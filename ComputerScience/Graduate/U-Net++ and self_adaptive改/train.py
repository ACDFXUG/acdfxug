import os
os.environ["KMP_DUPLICATE_LIB_OK"] = "TRUE"

import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader
from tqdm import tqdm
import lpips

# 尝试导入 piq，如果没有则使用替代方案
try:
    from piq import SSIMLoss
    HAS_PIQ = True
except ImportError:
    HAS_PIQ = False
    print("⚠️ piq 未安装，使用替代 SSIM 实现")

from dataset import MedicalRestorationDataset, transform
from unet_plusplus_adaptive import UNetPlusPlusAdaptive 
from degradation_simulator import apply_realistic_degradation

# ==================== 配置区 ====================
DATA_ROOT = '../MRI-Images-of-Brain-Tumor/timri/train'
BATCH_SIZE = 8
NUM_EPOCHS = 50
LEARNING_RATE = 1e-4
DEVICE = torch.device("cuda" if torch.cuda.is_available() else "cpu")
SAVE_DIR = "./checkpoints" 
os.makedirs(SAVE_DIR, exist_ok=True)

NUM_WORKERS = 4 

# ⚖️ 修正后的损失权重（FFT Loss 已归一化）
LAMBDA_MSE = 1.0      
LAMBDA_LPIPS = 0.1    
LAMBDA_SSIM = 0.5     # 增大SSIM权重（使用1-SSIM作为损失）
LAMBDA_FFT = 1.0      # 归一化后的FFT Loss权重

# ==================== 辅助函数 ====================
def fft_loss(pred, target):
    """
    归一化频域损失（Focal Frequency Loss 简化版）
    参考：Jiang et al., ICCV 2021 [^44^]
    """
    # 2D FFT
    pred_fft = torch.fft.fft2(pred)
    target_fft = torch.fft.fft2(target)
    
    # 幅度谱
    pred_mag = torch.abs(pred_fft)
    target_mag = torch.abs(target_fft)
    
    # 关键：归一化使损失与图像尺寸无关 [^44^]
    h, w = pred.shape[-2:]
    pred_mag = pred_mag / (h * w) ** 0.5
    target_mag = target_mag / (h * w) ** 0.5
    
    return torch.mean((pred_mag - target_mag) ** 2)

# 替代SSIM实现（如果没有piq）
class SSIMLossAlternative(nn.Module):
    def __init__(self):
        super().__init__()
        self.mu_x_pool = nn.AvgPool2d(3, 1, 1)
        self.mu_y_pool = nn.AvgPool2d(3, 1, 1)
        self.sig_x_pool = nn.AvgPool2d(3, 1, 1)
        self.sig_y_pool = nn.AvgPool2d(3, 1, 1)
        self.sig_xy_pool = nn.AvgPool2d(3, 1, 1)
        
        self.refl = nn.ReflectionPad2d(1)
        self.C1 = 0.01 ** 2
        self.C2 = 0.03 ** 2
        
    def forward(self, x, y):
        x = self.refl(x)
        y = self.refl(y)
        
        mu_x = self.mu_x_pool(x)
        mu_y = self.mu_y_pool(y)
        
        sigma_x = self.sig_x_pool(x**2) - mu_x**2
        sigma_y = self.sig_y_pool(y**2) - mu_y**2
        sigma_xy = self.sig_xy_pool(x*y) - mu_x*mu_y
        
        SSIM_n = (2*mu_x*mu_y + self.C1) * (2*sigma_xy + self.C2)
        SSIM_d = (mu_x**2 + mu_y**2 + self.C1) * (sigma_x + sigma_y + self.C2)
        
        return torch.clamp((1 - SSIM_n/SSIM_d).mean(), 0, 1)

def main():
    print(f"🚀 使用设备：{DEVICE}")
    print(f"📂 数据路径：{DATA_ROOT}")
    print(f"🏗️ 模型架构：UNetPlusPlusAdaptive")
    print(f"⚖️ 损失策略：MSE ({LAMBDA_MSE}) + LPIPS ({LAMBDA_LPIPS}) + SSIM ({LAMBDA_SSIM}) + FFT ({LAMBDA_FFT})")

    dataset = MedicalRestorationDataset(DATA_ROOT, transform=transform)
    train_loader = DataLoader(
        dataset, 
        batch_size=BATCH_SIZE, 
        shuffle=True, 
        num_workers=NUM_WORKERS, 
        pin_memory=True if DEVICE.type == 'cuda' else False
    )

    model = UNetPlusPlusAdaptive().to(DEVICE)
    
    criterion_mse = nn.MSELoss()
    loss_fn_lpips = lpips.LPIPS(net='alex', version='0.1').to(DEVICE)
    loss_fn_lpips.eval() 

    # SSIM Loss
    if HAS_PIQ:
        criterion_ssim = SSIMLoss(data_range=1.0, reduction='mean')
    else:
        criterion_ssim = SSIMLossAlternative()
    
    optimizer = optim.Adam(model.parameters(), lr=LEARNING_RATE)
    scheduler = optim.lr_scheduler.ReduceLROnPlateau(optimizer, mode='min', factor=0.5, patience=5)

    print(f"📦 数据集大小：{len(dataset)} 张图像")
    best_loss = float('inf')

    for epoch in range(NUM_EPOCHS):
        model.train()
        total_loss = 0.0
        total_mse = 0.0
        total_lpips = 0.0
        total_ssim = 0.0
        total_fft = 0.0
        
        pbar = tqdm(train_loader, desc=f"Epoch {epoch+1}/{NUM_EPOCHS}")

        # 课程学习策略
        if epoch < 15:
            current_severity = 'light'
        elif epoch < 35:
            current_severity = 'medium'
        else:
            current_severity = 'heavy'

        for clean_imgs, _ in pbar:
            clean_imgs = clean_imgs.to(DEVICE)

            # 动态生成退化图像
            degraded_imgs = apply_realistic_degradation(clean_imgs, deg_type='mixed', severity=current_severity)

            # 前向传播
            restored_imgs = model(degraded_imgs)

            if torch.isnan(restored_imgs).any() or torch.isinf(restored_imgs).any():
                print(f"⚠️ Epoch {epoch+1}: 检测到nan/inf，跳过")
                optimizer.zero_grad()
                continue
            
            # --- 计算损失 ---
            
            # 1. MSE Loss
            mse_loss = criterion_mse(restored_imgs, clean_imgs)
            
            # 2. LPIPS Loss
            lpips_loss = loss_fn_lpips(restored_imgs, clean_imgs).mean()
            
            # 3. SSIM Loss（转换到 [0, 1]）
            restored_norm = torch.clamp((restored_imgs + 1) / 2.0, 0, 1)
            clean_norm = torch.clamp((clean_imgs + 1) / 2.0, 0, 1)
            ssim_loss_val = criterion_ssim(restored_norm, clean_norm)
            
            # 4. FFT Loss（已归一化）
            fft_loss_val = fft_loss(restored_imgs, clean_imgs)

            # 总损失（平衡权重）
            loss = (LAMBDA_MSE * mse_loss) + \
                   (LAMBDA_LPIPS * lpips_loss) + \
                   (LAMBDA_SSIM * ssim_loss_val) + \
                   (LAMBDA_FFT * fft_loss_val)

            optimizer.zero_grad()
            loss.backward()
            torch.nn.utils.clip_grad_norm_(model.parameters(), max_norm=1.0)
            optimizer.step()

            # 记录
            total_loss += loss.item()
            total_mse += mse_loss.item()
            total_lpips += lpips_loss.item()
            total_ssim += ssim_loss_val.item()
            total_fft += fft_loss_val.item()
            
            pbar.set_postfix({
                "Total": f"{loss.item():.4f}", 
                "MSE": f"{mse_loss.item():.4f}", 
                "LPIPS": f"{lpips_loss.item():.4f}",
                "SSIM": f"{ssim_loss_val.item():.4f}",
                "FFT": f"{fft_loss_val.item():.4f}"
            })

        avg_loss = total_loss / len(train_loader)
        print(f"\n✅ Epoch {epoch+1} | Loss: {avg_loss:.4f} | MSE: {total_mse/len(train_loader):.4f} | LPIPS: {total_lpips/len(train_loader):.4f} | SSIM: {total_ssim/len(train_loader):.4f} | FFT: {total_fft/len(train_loader):.4f}")

        scheduler.step(avg_loss)

        if avg_loss < best_loss:
            best_loss = avg_loss
            torch.save(model.state_dict(), os.path.join(SAVE_DIR, "unet_best.pth"))

        if (epoch + 1) % 5 == 0:
            torch.save(model.state_dict(), os.path.join(SAVE_DIR, f"unet_epoch_{epoch+1}.pth"))

    torch.save(model.state_dict(), os.path.join(SAVE_DIR, "unet_final.pth"))
    print(f"🎉 训练完成！")

if __name__ == '__main__':
    main()