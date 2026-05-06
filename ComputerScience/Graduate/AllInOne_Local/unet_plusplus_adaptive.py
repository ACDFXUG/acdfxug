import torch
import torch.nn as nn
import torch.nn.functional as F

class DegradationEstimator(nn.Module):
    """轻量级退化估计器"""
    def __init__(self, in_channels=1, embed_dim=64):
        super().__init__()
        self.encoder = nn.Sequential(
            nn.Conv2d(in_channels, 32, 3, stride=2, padding=1),
            nn.BatchNorm2d(32), nn.ReLU(inplace=True),
            nn.Conv2d(32, 64, 3, stride=2, padding=1),
            nn.BatchNorm2d(64), nn.ReLU(inplace=True),
            nn.Conv2d(64, 128, 3, stride=2, padding=1),
            nn.BatchNorm2d(128), nn.ReLU(inplace=True),
            nn.AdaptiveAvgPool2d(1), nn.Flatten(),
            nn.Linear(128, embed_dim)
        )
    def forward(self, x): 
        return self.encoder(x)


class FullSpatialAdaptiveModulationLayer(nn.Module):
    """
    完全空间自适应调制层
    结合退化嵌入和局部特征，为每个像素生成独特的 gamma/beta
    """
    def __init__(self, feature_channels, embed_dim=64, reduction=4):
        super().__init__()
        # 空间调制图生成器：融合局部特征 + 退化信息
        self.spatial_mlp = nn.Sequential(
            nn.Conv2d(feature_channels + embed_dim, feature_channels // reduction, 1),
            nn.ReLU(inplace=True),
            nn.Conv2d(feature_channels // reduction, feature_channels * 2, 1)  # 输出 gamma+beta
        )
        
    def forward(self, features, deg_embedding):
        B, C, H, W = features.shape
        
        # Instance Norm（逐通道逐样本归一化）
        mu = features.mean(dim=[2, 3], keepdim=True)
        var = features.var(dim=[2, 3], keepdim=True, unbiased=False)
        sigma = torch.sqrt(var + 1e-5)
        features_norm = (features - mu) / sigma
        
        # 将退化嵌入扩展为空间图（与特征图空间对齐）
        deg_map = deg_embedding.view(B, -1, 1, 1).expand(-1, -1, H, W)  # [B, embed_dim, H, W]
        
        # 融合局部特征和退化信息，生成空间调制图
        gamma_beta_map = self.spatial_mlp(torch.cat([features_norm, deg_map], dim=1))  # [B, 2*C, H, W]
        gamma_map, beta_map = gamma_beta_map.chunk(2, dim=1)
        
        # 可选：用 tanh 约束调制范围，防止训练不稳定（gamma 可正可负）
        gamma_map = torch.tanh(gamma_map) * 2  # 范围 [-2, 2]
        beta_map = torch.tanh(beta_map) * 2   # 范围 [-2, 2]
        
        out = gamma_map * features_norm + beta_map
        return torch.nan_to_num(out, nan=0.0, posinf=5.0, neginf=-5.0)


class DoubleConv(nn.Module):
    def __init__(self, in_ch, out_ch):
        super().__init__()
        self.conv = nn.Sequential(
            nn.Conv2d(in_ch, out_ch, 3, padding=1),
            nn.BatchNorm2d(out_ch), nn.ReLU(inplace=True),
            nn.Conv2d(out_ch, out_ch, 3, padding=1),
            nn.BatchNorm2d(out_ch), nn.ReLU(inplace=True)
        )
    def forward(self, x): 
        return self.conv(x)


class UNetPlusPlusAdaptive(nn.Module):
    """
    U-Net++ with 完全空间自适应调制 (方案二)
    在多个解码阶段注入空间自适应调制，解决退化强度空间不均问题
    """
    def __init__(self, n_channels=1, n_classes=1, base_filters=64, embed_dim=64):
        super().__init__()
        
        # 退化估计器（全程共享）
        self.deg_estimator = DegradationEstimator(n_channels, embed_dim)
        
        # Encoder: X^{0,0}, X^{1,0}, X^{2,0}
        self.enc0 = DoubleConv(n_channels, base_filters)
        self.enc1 = DoubleConv(base_filters, base_filters*2)
        self.enc2 = DoubleConv(base_filters*2, base_filters*4)
        self.pool = nn.MaxPool2d(2)
        
        # Center: X^{3,0}
        self.center_conv = DoubleConv(base_filters*4, base_filters*8)
        
        # ========== 空间自适应调制层（注入多个阶段）==========
        self.spatial_adain_center = FullSpatialAdaptiveModulationLayer(base_filters*8, embed_dim)
        self.spatial_adain_2_1 = FullSpatialAdaptiveModulationLayer(base_filters*4, embed_dim)
        self.spatial_adain_1_2 = FullSpatialAdaptiveModulationLayer(base_filters*2, embed_dim)
        self.spatial_adain_0_3 = FullSpatialAdaptiveModulationLayer(base_filters, embed_dim)
        # ===================================================
        
        # Decoder（按U-Net++公式构建）
        # Level 2: X^{2,1} = H([X^{2,0}, U(X^{3,0})])
        self.conv2_1 = DoubleConv(base_filters*4 + base_filters*8, base_filters*4)
        
        # Level 1: X^{1,1}, X^{1,2}
        self.conv1_1 = DoubleConv(base_filters*2 + base_filters*4, base_filters*2)
        self.conv1_2 = DoubleConv(base_filters*2 * 2 + base_filters*4, base_filters*2)
        
        # Level 0: X^{0,1}, X^{0,2}, X^{0,3}
        self.conv0_1 = DoubleConv(base_filters + base_filters*2, base_filters)
        self.conv0_2 = DoubleConv(base_filters * 2 + base_filters*2, base_filters)
        self.conv0_3 = DoubleConv(base_filters * 3 + base_filters*2, base_filters)
        
        self.final = nn.Conv2d(base_filters, n_classes, 1)
        self.up = lambda x, size: F.interpolate(x, size=size, mode='bilinear', align_corners=False)

    def forward(self, x):
        # 1. 估计退化特征（只算一次，全程共享）
        deg_embedding = self.deg_estimator(x)
        
        # 2. Encoder
        x0_0 = self.enc0(x)                     # [B, 64, H, W]
        x1_0 = self.enc1(self.pool(x0_0))       # [B, 128, H/2, W/2]
        x2_0 = self.enc2(self.pool(x1_0))       # [B, 256, H/4, W/4]
        
        # 3. Center + 空间自适应调制
        x3_0 = self.center_conv(self.pool(x2_0))      # [B, 512, H/8, W/8]
        x3_0 = self.spatial_adain_center(x3_0, deg_embedding)
        
        # 4. Decoder（密集连接，每个阶段后应用空间自适应调制）
        # Level 2
        x2_1 = self.conv2_1(torch.cat([x2_0, self.up(x3_0, x2_0.shape[2:])], dim=1))
        x2_1 = self.spatial_adain_2_1(x2_1, deg_embedding)
        
        # Level 1
        x1_1 = self.conv1_1(torch.cat([x1_0, self.up(x2_0, x1_0.shape[2:])], dim=1))
        # 注意：x1_2 需要使用 x2_1 上采样后的特征
        x1_2_input = torch.cat([x1_0, x1_1, self.up(x2_1, x1_0.shape[2:])], dim=1)
        x1_2 = self.conv1_2(x1_2_input)
        x1_2 = self.spatial_adain_1_2(x1_2, deg_embedding)
        
        # Level 0
        x0_1 = self.conv0_1(torch.cat([x0_0, self.up(x1_0, x0_0.shape[2:])], dim=1))
        
        x0_2_input = torch.cat([x0_0, x0_1, self.up(x1_1, x0_0.shape[2:])], dim=1)
        x0_2 = self.conv0_2(x0_2_input)
        
        x0_3_input = torch.cat([x0_0, x0_1, x0_2, self.up(x1_2, x0_0.shape[2:])], dim=1)
        x0_3 = self.conv0_3(x0_3_input)
        x0_3 = self.spatial_adain_0_3(x0_3, deg_embedding)
        
        return self.final(x0_3)


if __name__ == "__main__":
    model = UNetPlusPlusAdaptive()
    x = torch.rand(2, 1, 224, 224)
    y = model(x)
    print(f"Input: {x.shape}, Output: {y.shape}")
    print(f"Params: {sum(p.numel() for p in model.parameters())/1e6:.2f}M")
    
    # 测试空间自适应特性：打印调制层的输出统计
    with torch.no_grad():
        _ = model(x)
        # 可以在这里添加断点，观察 gamma_map/beta_map 的空间变化
        print("Model forward pass completed. Spatial modulation maps are generated per pixel.")