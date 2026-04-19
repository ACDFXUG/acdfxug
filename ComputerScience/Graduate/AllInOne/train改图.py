import os
os.environ["KMP_DUPLICATE_LIB_OK"] = "TRUE"
import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader
from tqdm import tqdm
import lpips
import random
import json
import matplotlib.pyplot as plt  # 📊 新增：用于画图

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
from MPRNet import MPRNet

# ==================== 配置区 ====================
DATA_ROOT = '../MRI-Images-of-Brain-Tumor/timri/train'
BATCH_SIZE = 8
NUM_EPOCHS = 50
LEARNING_RATE = 1e-4
DEVICE = torch.device("cuda" if torch.cuda.is_available() else "cpu")
SAVE_DIR = "./checkpoints"
os.makedirs(SAVE_DIR, exist_ok=True)
NUM_WORKERS = 4
MODEL_TYPE='Ours'
DEG_TYPES=['noise','blur','low_light','mixed']
DEG_TYPE=DEG_TYPES[1]

# ⚖️ 损失权重
LAMBDA_MSE = 1.0
LAMBDA_LPIPS = 0.1
LAMBDA_SSIM = 0.5
LAMBDA_FFT = 1.0

# ==================== 辅助函数 ====================
def fft_loss(pred, target):
    """归一化频域损失"""
    pred_fft = torch.fft.fft2(pred)
    target_fft = torch.fft.fft2(target)
    pred_mag = torch.abs(pred_fft)
    target_mag = torch.abs(target_fft)
    h, w = pred.shape[-2:]
    pred_mag = pred_mag / (h * w) ** 0.5
    target_mag = target_mag / (h * w) ** 0.5
    return torch.mean((pred_mag - target_mag) ** 2)

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

def get_severity_distribution(epoch):
    if epoch < 15: return {'light': 0.70, 'medium': 0.25, 'heavy': 0.05}
    elif epoch < 35: return {'light': 0.20, 'medium': 0.60, 'heavy': 0.20}
    else: return {'light': 0.15, 'medium': 0.35, 'heavy': 0.50}

def sample_severity(distribution):
    rand = random.random()
    cumulative = 0.0
    for severity, prob in distribution.items():
        cumulative += prob
        if rand < cumulative: return severity
    return 'heavy'

# 📊 新增：绘制训练曲线函数
def plot_training_history(history, save_dir):
    epochs = history["epoch"]
    fig, axes = plt.subplots(3, 1, figsize=(10, 12))
    fig.suptitle("Training History", fontsize=16, fontweight='bold')
    
    # 1. Total Loss
    axes[0].plot(epochs, history["loss"], marker='o', linestyle='-', color='b', label='Total Loss')
    axes[0].set_ylabel("Loss")
    axes[0].legend()
    axes[0].grid(True, alpha=0.3)
    
    # 2. LPIPS Loss
    axes[1].plot(epochs, history["lpips"], marker='s', linestyle='-', color='g', label='LPIPS Loss')
    axes[1].set_ylabel("LPIPS Loss")
    axes[1].legend()
    axes[1].grid(True, alpha=0.3)
    
    # 3. SSIM Loss (注意：这里是损失值，越小越好。若需显示SSIM指标可改为 1 - ssim_loss)
    axes[2].plot(epochs, history["ssim"], marker='^', linestyle='-', color='r', label='SSIM Loss')
    axes[2].set_ylabel("SSIM Loss")
    axes[2].set_xlabel("Epoch")
    axes[2].legend()
    axes[2].grid(True, alpha=0.3)
    
    plt.tight_layout()
    plot_path = os.path.join(save_dir, "training_curves.png")
    plt.savefig(plot_path, dpi=300)
    print(f"📈 训练曲线已保存至: {plot_path}")
    plt.close()

def main():
    print(f"🚀 使用设备：{DEVICE}")
    print(f"📂 数据路径：{DATA_ROOT}")
    print(f"🏗️ 模型架构：{MODEL_TYPE}")
    print(f"⚖️ 损失策略：MSE + LPIPS + SSIM + FFT")
    print(f"📊 训练策略：混合退化程度 (防止灾难性遗忘)")
    print(f"退化策略：{DEG_TYPE}")
    
    dataset = MedicalRestorationDataset(DATA_ROOT, transform=transform)
    train_loader = DataLoader(
        dataset, batch_size=BATCH_SIZE, shuffle=True, 
        num_workers=NUM_WORKERS, pin_memory=(DEVICE.type == 'cuda')
    )

    # model = UNetPlusPlusAdaptive().to(DEVICE)
    if MODEL_TYPE=='Ours':
        model=UNetPlusPlusAdaptive().to(DEVICE)
    elif MODEL_TYPE=='MPRNet':
        model=MPRNet(in_c=1,out_c=1,n_feat=20,scale_unetfeats=12,scale_orsnetfeats=8,num_cab=4).to(DEVICE)
    criterion_mse = nn.MSELoss()
    loss_fn_lpips = lpips.LPIPS(net='alex', version='0.1').to(DEVICE)
    loss_fn_lpips.eval() 

    criterion_ssim = SSIMLoss(data_range=1.0, reduction='mean') if HAS_PIQ else SSIMLossAlternative()

    optimizer = optim.Adam(model.parameters(), lr=LEARNING_RATE)
    scheduler = optim.lr_scheduler.ReduceLROnPlateau(optimizer, mode='min', factor=0.5, patience=5)

    print(f"📦 数据集大小：{len(dataset)} 张图像")
    
    # 📊 新增：初始化历史记录容器
    history = {"epoch": [], "loss": [], "lpips": [], "ssim": []}
    best_loss = float('inf')

    for epoch in range(NUM_EPOCHS):
        model.train()
        total_loss = 0.0
        total_mse = 0.0
        total_lpips = 0.0
        total_ssim = 0.0
        total_fft = 0.0
        
        severity_dist = get_severity_distribution(epoch)
        print(f"\n📊 Epoch {epoch+1} 退化分布: {severity_dist}")

        pbar = tqdm(train_loader, desc=f"Epoch {epoch+1}/{NUM_EPOCHS}")
        n_batches = 0  # 📊 新增：用于安全计算平均值

        for clean_imgs, _ in pbar:
            clean_imgs = clean_imgs.to(DEVICE)
            batch_size = clean_imgs.size(0)
            
            degraded_imgs_list = []
            for i in range(batch_size):
                severity = sample_severity(severity_dist)
                degraded = apply_realistic_degradation(
                    clean_imgs[i:i+1], deg_type=DEG_TYPE, severity=severity
                )
                degraded_imgs_list.append(degraded)
            degraded_imgs = torch.cat(degraded_imgs_list, dim=0)

            restored_imgs = model(degraded_imgs)

            if torch.isnan(restored_imgs).any() or torch.isinf(restored_imgs).any():
                print(f"⚠️ Epoch {epoch+1}: 检测到nan/inf，跳过")
                optimizer.zero_grad()
                continue
            
            mse_loss = criterion_mse(restored_imgs, clean_imgs) 
            with torch.no_grad():
                lpips_loss = loss_fn_lpips(restored_imgs, clean_imgs).mean()
            
            restored_norm = torch.clamp((restored_imgs + 1) / 2.0, 0, 1)
            clean_norm = torch.clamp((clean_imgs + 1) / 2.0, 0, 1)
            ssim_loss_val = criterion_ssim(restored_norm, clean_norm)
            fft_loss_val = fft_loss(restored_imgs, clean_imgs)

            loss = (LAMBDA_MSE * mse_loss) + \
                   (LAMBDA_LPIPS * lpips_loss) + \
                   (LAMBDA_SSIM * ssim_loss_val) + \
                   (LAMBDA_FFT * fft_loss_val)

            optimizer.zero_grad()
            loss.backward()
            torch.nn.utils.clip_grad_norm_(model.parameters(), max_norm=1.0)
            optimizer.step()

            total_loss += loss.item()
            total_lpips += lpips_loss.item()
            total_ssim += ssim_loss_val.item()
            n_batches += 1
            
            pbar.set_postfix({
                "Total": f"{loss.item():.4f}", 
                "MSE": f"{mse_loss.item():.4f}", 
                "LPIPS": f"{lpips_loss.item():.4f}",
                "SSIM": f"{ssim_loss_val.item():.4f}",
                "FFT": f"{fft_loss_val.item():.4f}"
            })

        # 📊 新增：计算当前 Epoch 的平均值并记录
        if n_batches > 0:
            avg_loss = total_loss / n_batches
            avg_lpips = total_lpips / n_batches
            avg_ssim = total_ssim / n_batches
            
            history["epoch"].append(epoch + 1)
            history["loss"].append(avg_loss)
            history["lpips"].append(avg_lpips)
            history["ssim"].append(avg_ssim)
            
            print(f"\n✅ Epoch {epoch+1} | Avg Loss: {avg_loss:.4f} | Avg LPIPS: {avg_lpips:.4f} | Avg SSIM Loss: {avg_ssim:.4f}")
            scheduler.step(avg_loss)

            if avg_loss < best_loss:
                best_loss = avg_loss
                torch.save(model.state_dict(), os.path.join(SAVE_DIR, "unet_best.pth"))
                print(f"   💾 保存最佳模型 (loss: {best_loss:.4f})")

            if (epoch + 1) % 5 == 0:
                torch.save(model.state_dict(), os.path.join(SAVE_DIR, f"unet_epoch_{epoch+1}.pth"))
                print(f"   💾 保存 epoch {epoch+1} checkpoint")

    # 📊 训练结束后保存历史记录
    history_path = os.path.join(SAVE_DIR, "training_history.json")
    with open(history_path, "w") as f:
        json.dump(history, f)
    print(f"\n📊 训练指标已保存至: {history_path}")
    
    # 📊 自动绘制折线图
    plot_training_history(history, SAVE_DIR)

    torch.save(model.state_dict(), os.path.join(SAVE_DIR, "unet_final.pth"))
    print(f"\n🎉 训练完成！")
    print(f"📁 模型保存路径：{SAVE_DIR}")

if __name__ == '__main__':
    main()