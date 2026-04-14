import torch
import torch.nn as nn


class DoubleConv(nn.Module):
    def __init__(self, in_ch, out_ch):
        super().__init__()
        self.conv = nn.Sequential(
            nn.Conv2d(in_ch, out_ch, 3, padding=1),
            nn.BatchNorm2d(out_ch),
            nn.ReLU(inplace=True),
            nn.Conv2d(out_ch, out_ch, 3, padding=1),
            nn.BatchNorm2d(out_ch),
            nn.ReLU(inplace=True)
        )
    def forward(self, x): return self.conv(x)

class UNetPlusPlus(nn.Module):
    def __init__(self, n_channels=1, n_classes=1, base_filters=64):
        super().__init__()
        self.base_filters = base_filters
        
        # Encoders
        self.enc1 = DoubleConv(n_channels, base_filters)
        self.enc2 = DoubleConv(base_filters, base_filters*2)
        self.enc3 = DoubleConv(base_filters*2, base_filters*4)
        self.enc4 = DoubleConv(base_filters*4, base_filters*8)
        self.pool = nn.MaxPool2d(2)
        
        # Center
        self.center = DoubleConv(base_filters*8, base_filters*16)
        
        # Decoders (输入通道数 = 上层上采样 + 同层左侧 + 同层右侧? 不，U-Net++ 是密集连接)
        # 为了简化并保证通道匹配，我们定义具体的卷积层来处理拼接
        # Xij: i 是 encoder 阶段，j 是 decoder 阶段
        
        # Decoder Stage 4 (Input: center up + enc4) -> Channel: 1024+512 = 1536 -> 512
        self.dec4_0 = DoubleConv(base_filters*16 + base_filters*8, base_filters*8)
        
        # Decoder Stage 3 
        # X30 = enc3 (512)
        # X31 = cat(up(X40), X30) -> 512+256=768 -> 256
        self.dec3_0 = DoubleConv(base_filters*8 + base_filters*4, base_filters*4)
        # X32 = cat(up(X41), X31) ... 这种全连接太复杂，我们实现一个**实用的 U-Net++ 变体**
        # 即：在每一级 skip connection 中加入卷积块，而不是简单的 concat
        
        # --- 实用版 U-Net++ 架构 (保留核心思想：嵌套密集连接，但简化通道管理) ---
        # 上采样
        self.up4 = nn.ConvTranspose2d(base_filters*16, base_filters*8, 2, stride=2)
        self.up3 = nn.ConvTranspose2d(base_filters*8, base_filters*4, 2, stride=2)
        self.up2 = nn.ConvTranspose2d(base_filters*4, base_filters*2, 2, stride=2)
        self.up1 = nn.ConvTranspose2d(base_filters*2, base_filters, 2, stride=2)
        
        # 密集卷积块 (处理拼接后的特征)
        # Level 4
        self.conv4_0 = DoubleConv(base_filters*8 * 2, base_filters*8) # cat(up_center, enc4)
        
        # Level 3
        self.conv3_0 = DoubleConv(base_filters*4 * 2, base_filters*4) # cat(up_enc4, enc3)
        self.conv3_1 = DoubleConv(base_filters*4 * 2, base_filters*4) # cat(up_conv4_0, conv3_0)
        
        # Level 2
        self.conv2_0 = DoubleConv(base_filters*2 * 2, base_filters*2)
        self.conv2_1 = DoubleConv(base_filters*2 * 2, base_filters*2)
        self.conv2_2 = DoubleConv(base_filters*2 * 2, base_filters*2)
        
        # Level 1
        self.conv1_0 = DoubleConv(base_filters * 2, base_filters)
        self.conv1_1 = DoubleConv(base_filters * 2, base_filters)
        self.conv1_2 = DoubleConv(base_filters * 2, base_filters)
        self.conv1_3 = DoubleConv(base_filters * 2, base_filters) # 最终输出层
        
        self.final = nn.Conv2d(base_filters, n_classes, 1)

    def forward(self, x):
        # Encoder
        e1 = self.enc1(x)
        e2 = self.enc2(self.pool(e1))
        e3 = self.enc3(self.pool(e2))
        e4 = self.enc4(self.pool(e3))
        
        # Center
        c = self.center(self.pool(e4))
        
        # Decoder Level 4
        u4 = self.up4(c)
        x40 = e4
        x41 = self.conv4_0(torch.cat([u4, x40], dim=1))
        
        # Decoder Level 3
        u3_from_40 = self.up3(x41) # 从 x41 上采样
        u3_from_c = self.up3(u4)   # 其实不需要这个，U-Net++ 逻辑是：
        # X30 = e3
        # X31 = conv(cat(up(X40), X30)) -> 这里的 X40 指的是 decoder 第一级的输出
        # 让我们用最标准的 U-Net++ 逻辑重写 forward，确保无误：
        
        # --- 标准 U-Net++ Forward ---
        # Row 0 (Encoders)
        x00 = e1
        x10 = e2
        x20 = e3
        x30 = e4
        
        # Row 1
        # x01 needs up(x10) and x00. But wait, standard UNet++ connects differently.
        # Let's use a simplified but effective implementation often used in competitions:
        # Deep Supervision style or just dense skip connections.
        
        # 重新梳理：
        # x00 = enc1
        # x10 = enc2
        # x20 = enc3
        # x30 = enc4
        # center = bottleneck
        
        # x31 = conv(cat(up(center), x30))
        x31 = self.conv4_0(torch.cat([self.up4(c), x30], dim=1))
        
        # x21 = conv(cat(up(x30), x20))  <- 传统 U-Net 路径
        # x22 = conv(cat(up(x31), x21))  <- U-Net++ 新增路径
        x20_proj = x20
        x21 = self.conv3_0(torch.cat([self.up3(x30), x20_proj], dim=1))
        x22 = self.conv3_1(torch.cat([self.up3(x31), x21], dim=1))
        
        # x11 = conv(cat(up(x20), x10))
        # x12 = conv(cat(up(x21), x11))
        # x13 = conv(cat(up(x22), x12))
        x10_proj = x10
        x11 = self.conv2_0(torch.cat([self.up2(x20_proj), x10_proj], dim=1))
        x12 = self.conv2_1(torch.cat([self.up2(x21), x11], dim=1))
        x13 = self.conv2_2(torch.cat([self.up2(x22), x12], dim=1))
        
        # x01... x04 (Output level)
        # x01 = conv(cat(up(x10), x00))
        # ...
        # x04 = final output
        x00_proj = x00
        x01 = self.conv1_0(torch.cat([self.up1(x10_proj), x00_proj], dim=1))
        x02 = self.conv1_1(torch.cat([self.up1(x11), x01], dim=1))
        x03 = self.conv1_2(torch.cat([self.up1(x12), x02], dim=1))
        x04 = self.conv1_3(torch.cat([self.up1(x13), x03], dim=1))
        
        return self.final(x04)

# 实例化测试
if __name__ == "__main__":
    model = UNetPlusPlus()
    x = torch.rand(2, 1, 224, 224)
    y = model(x)
    print(f"Input: {x.shape}, Output: {y.shape}")
    # 预期输出：torch.Size([2, 1, 224, 224])