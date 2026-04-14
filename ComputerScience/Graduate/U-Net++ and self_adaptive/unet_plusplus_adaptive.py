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

class AdaptiveModulationLayer(nn.Module):
    """AdaIN 调制层"""
    def __init__(self, feature_channels, embed_dim=64):
        super().__init__()
        self.gamma_net = nn.Linear(embed_dim, feature_channels)
        self.beta_net = nn.Linear(embed_dim, feature_channels)
        nn.init.constant_(self.gamma_net.weight, 0)
        nn.init.constant_(self.gamma_net.bias, 1)
        nn.init.constant_(self.beta_net.weight, 0)
        nn.init.constant_(self.beta_net.bias, 0)
    
    def forward(self, features, deg_embedding):
        B, C, H, W = features.shape
        mu = features.mean(dim=[2, 3], keepdim=True)
        sigma = features.std(dim=[2, 3], keepdim=True) + 1e-5
        gamma = self.gamma_net(deg_embedding).view(B, C, 1, 1)
        beta = self.beta_net(deg_embedding).view(B, C, 1, 1)
        return gamma * (features - mu) / sigma + beta

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
    U-Net++ with 自适应退化调制 (L=3 轻量版)
    公式: X^{i,j} = H([X^{i,k}]_{k=0}^{j-1}, U(X^{i+1,j-1})) for j>0
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
        
        # Center: X^{3,0} + AdaIN调制
        self.center_conv = DoubleConv(base_filters*4, base_filters*8)
        self.adain_center = AdaptiveModulationLayer(base_filters*8, embed_dim)
        
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
        x0_0 = self.enc0(x)           # 224
        x1_0 = self.enc1(self.pool(x0_0))   # 112
        x2_0 = self.enc2(self.pool(x1_0))   # 56
        
        # 3. Center + 自适应调制
        x3_0 = self.center_conv(self.pool(x2_0))  # 28
        x3_0 = self.adain_center(x3_0, deg_embedding)
        
        # 4. Decoder（密集连接）
        # Level 2
        x2_1 = self.conv2_1(torch.cat([x2_0, self.up(x3_0, x2_0.shape[2:])], dim=1))
        
        # Level 1
        x1_1 = self.conv1_1(torch.cat([x1_0, self.up(x2_0, x1_0.shape[2:])], dim=1))
        x1_2 = self.conv1_2(torch.cat([x1_0, x1_1, self.up(x2_1, x1_0.shape[2:])], dim=1))
        
        # Level 0
        x0_1 = self.conv0_1(torch.cat([x0_0, self.up(x1_0, x0_0.shape[2:])], dim=1))
        x0_2 = self.conv0_2(torch.cat([x0_0, x0_1, self.up(x1_1, x0_0.shape[2:])], dim=1))
        x0_3 = self.conv0_3(torch.cat([x0_0, x0_1, x0_2, self.up(x1_2, x0_0.shape[2:])], dim=1))
        
        return self.final(x0_3)

# 测试
if __name__ == "__main__":
    model = UNetPlusPlusAdaptive()
    x = torch.rand(2, 1, 224, 224)
    y = model(x)
    print(f"Input: {x.shape}, Output: {y.shape}")
    print(f"Params: {sum(p.numel() for p in model.parameters())/1e6:.2f}M")
