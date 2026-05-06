import os
# 解决 Windows OMP 报错
os.environ["KMP_DUPLICATE_LIB_OK"] = "TRUE"

import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader
from tqdm import tqdm
import lpips
import json
import matplotlib.pyplot as plt  # 📊 新增：用于画图

# 尝试导入 piq，如果没有则使用替代方案
try:
    from piq import SSIMLoss
    HAS_PIQ = True
except ImportError:
    HAS_PIQ = False
    print("⚠️ piq 未安装，使用替代 SSIM 实现")

# 导入你之前写的模块
from dataset import MedicalRestorationDataset, get_data_loaders
from unet_plusplus_adaptive import UNetPlusPlusAdaptive
from MPRNet import MPRNet

# ==================== 配置区 ====================
BASE_DATA_ROOT = '../LoDoPaB-CT'  # LoDoPaB-CT数据集根目录
TRAIN_SIZE = 30  # 使用前30个hdf5文件进行训练
VAL_SIZE = 10    # 使用前10个hdf5文件进行验证
BATCH_SIZE = 4   # 根据内存情况调整批次大小
NUM_EPOCHS = 20
LEARNING_RATE = 1e-4
DEVICE = torch.device("cuda" if torch.cuda.is_available() else "cpu")
SAVE_DIR = "./checkpoints"
os.makedirs(SAVE_DIR, exist_ok=True)
NUM_WORKERS = 4 
MODEL_TYPE='Ours'  # 可选 'Ours' 或 'MPRNet'

# ⚖️ 损失权重 (参考 train改图.py 的配置)
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

# 📊 新增：绘制训练曲线函数
def plot_training_history(history, save_dir):
    epochs = history["epoch"]
    if not epochs:
        return
        
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
    print(f"📊 数据集：LoDoPaB-CT (训练集: {TRAIN_SIZE}个文件, 验证集: {VAL_SIZE}个文件)")
    print(f"🏗️ 模型架构：{MODEL_TYPE}")
    print(f"⚖️ 损失策略：MSE + LPIPS + SSIM + FFT")
    
    # 获取数据加载器
    train_loader, val_loader, _ = get_data_loaders(
        BASE_DATA_ROOT, 
        train_size=TRAIN_SIZE, 
        val_size=VAL_SIZE, 
        test_size=10,  # 预留测试集大小
        batch_size=BATCH_SIZE
    )

    for inputs, targets in train_loader:
        print(f"训练数据 - Input shape: {inputs.shape}, Target shape: {targets.shape}")
        break

    for inputs, targets in val_loader:
        print(f"验证数据 - Input shape: {inputs.shape}, Target shape: {targets.shape}")
        break

    # 模型
    # model = UNetPlusPlusAdaptive().to(DEVICE)
    if MODEL_TYPE=='Ours':
        model=UNetPlusPlusAdaptive().to(DEVICE)
        print(f"✅ 已选择模型: UNet++ 自适应模型")
    elif MODEL_TYPE=='MPRNet':
        model=MPRNet(in_c=1,out_c=1,n_feat=20,scale_unetfeats=12,scale_orsnetfeats=8,num_cab=4).to(DEVICE)
        print(f"✅ 已选择模型: MPRNet")
    else:
        raise ValueError(f"❌ 未知的模型类型: {MODEL_TYPE}")
    
    criterion_mse = nn.MSELoss()
    
    # LPIPS (输入范围 [-1, 1])
    loss_fn_lpips = lpips.LPIPS(net='alex', version='0.1').to(DEVICE)
    loss_fn_lpips.eval() 

    # SSIM Loss
    criterion_ssim = SSIMLoss(data_range=1.0, reduction='mean') if HAS_PIQ else SSIMLossAlternative()
    
    optimizer = optim.Adam(model.parameters(), lr=LEARNING_RATE)
    scheduler = optim.lr_scheduler.ReduceLROnPlateau(optimizer, mode='min', factor=0.5, patience=5)

    print(f"📦 训练数据集大小：约 {len(train_loader.dataset)} 张切片")
    print(f"📦 验证数据集大小：约 {len(val_loader.dataset)} 张切片")
    
    # 📊 新增：初始化历史记录容器
    history = {"epoch": [], "loss": [], "lpips": [], "ssim": []}
    best_loss = float('inf')

    for epoch in range(NUM_EPOCHS):
        # 训练阶段
        model.train()
        total_loss = 0.0
        total_mse = 0.0
        total_lpips = 0.0
        total_ssim = 0.0
        total_fft = 0.0
        
        pbar = tqdm(train_loader, desc=f"Train Epoch {epoch+1}/{NUM_EPOCHS}")
        n_batches = 0  # 📊 新增：用于安全计算平均值

        for inputs, targets in pbar:  # 直接获取真实观察值和真值
            inputs = inputs.to(DEVICE)  # [B, 1, 362, 362], range [-1, 1]
            targets = targets.to(DEVICE)  # [B, 1, 362, 362], range [-1, 1]

            restored_imgs = model(inputs)

            # 检测 NaN/Inf
            if torch.isnan(restored_imgs).any() or torch.isinf(restored_imgs).any():
                print(f"⚠️ Epoch {epoch+1}: 检测到nan/inf，跳过")
                optimizer.zero_grad()
                continue
            
            # 计算各项损失
            mse_loss = criterion_mse(restored_imgs, targets) 
            
            # LPIPS 需要输入范围是 [-1, 1]，正好符合
            # 使用 no_grad 加速，因为 LPIPS 不需要反向传播到其内部参数
            with torch.no_grad():
                lpips_loss = loss_fn_lpips(restored_imgs, targets).mean()
            
            # SSIM Loss 需要输入范围是 [0, 1]
            restored_norm = torch.clamp((restored_imgs + 1) / 2.0, 0, 1)
            targets_norm = torch.clamp((targets + 1) / 2.0, 0, 1)
            ssim_loss_val = criterion_ssim(restored_norm, targets_norm)
            
            # FFT 损失计算
            fft_loss_val = fft_loss(restored_imgs, targets)

            # 总损失
            loss = (LAMBDA_MSE * mse_loss) + \
                   (LAMBDA_LPIPS * lpips_loss) + \
                   (LAMBDA_SSIM * ssim_loss_val) + \
                   (LAMBDA_FFT * fft_loss_val)

            # 反向传播
            optimizer.zero_grad()
            loss.backward()
            # 梯度裁剪，防止梯度爆炸
            torch.nn.utils.clip_grad_norm_(model.parameters(), max_norm=1.0)
            optimizer.step()

            # 累积损失
            total_loss += loss.item()
            total_mse += mse_loss.item()
            total_lpips += lpips_loss.item()
            total_ssim += ssim_loss_val.item()
            total_fft += fft_loss_val.item()
            n_batches += 1
            
            pbar.set_postfix({
                "Total": f"{loss.item():.4f}", 
                "MSE": f"{mse_loss.item():.4f}", 
                "LPIPS": f"{lpips_loss.item():.4f}",
                "SSIM": f"{ssim_loss_val.item():.4f}",
                "FFT": f"{fft_loss_val.item():.4f}"
            })

        # 验证阶段
        model.eval()
        val_total_loss = 0.0
        val_total_lpips = 0.0
        val_total_ssim = 0.0
        val_n_batches = 0
        
        with torch.no_grad():
            val_pbar = tqdm(val_loader, desc=f"Val Epoch {epoch+1}/{NUM_EPOCHS}")
            for val_inputs, val_targets in val_pbar:
                val_inputs = val_inputs.to(DEVICE)
                val_targets = val_targets.to(DEVICE)
                
                val_restored = model(val_inputs)
                
                val_mse_loss = criterion_mse(val_restored, val_targets)
                val_lpips_loss = loss_fn_lpips(val_restored, val_targets).mean()
                
                val_restored_norm = torch.clamp((val_restored + 1) / 2.0, 0, 1)
                val_targets_norm = torch.clamp((val_targets + 1) / 2.0, 0, 1)
                val_ssim_loss = criterion_ssim(val_restored_norm, val_targets_norm)
                
                val_fft_loss = fft_loss(val_restored, val_targets)
                
                val_loss = (LAMBDA_MSE * val_mse_loss) + \
                          (LAMBDA_LPIPS * val_lpips_loss) + \
                          (LAMBDA_SSIM * val_ssim_loss) + \
                          (LAMBDA_FFT * val_fft_loss)
                
                val_total_loss += val_loss.item()
                val_total_lpips += val_lpips_loss.item()
                val_total_ssim += val_ssim_loss.item()
                val_n_batches += 1
                
                val_pbar.set_postfix({
                    "val_loss": f"{val_loss.item():.4f}"
                })

        # 📊 计算当前 Epoch 的平均值并记录
        if n_batches > 0 and val_n_batches > 0:
            avg_train_loss = total_loss / n_batches
            avg_train_lpips = total_lpips / n_batches
            avg_train_ssim = total_ssim / n_batches
            
            avg_val_loss = val_total_loss / val_n_batches
            avg_val_lpips = val_total_lpips / val_n_batches
            avg_val_ssim = val_total_ssim / val_n_batches
            
            history["epoch"].append(epoch + 1)
            history["loss"].append(avg_val_loss)  # 使用验证集的loss作为历史记录的loss
            history["lpips"].append(avg_val_lpips)
            history["ssim"].append(avg_val_ssim)
            
            print(f"\n✅ Train Epoch {epoch+1}: Avg Train Loss: {avg_train_loss:.4f} | Avg Train LPIPS: {avg_train_lpips:.4f} | Avg Train SSIM Loss: {avg_train_ssim:.4f}")
            print(f"✅ Val Epoch {epoch+1}: Avg Val Loss: {avg_val_loss:.4f} | Avg Val LPIPS: {avg_val_lpips:.4f} | Avg Val SSIM Loss: {avg_val_ssim:.4f}")
            
            # 更新学习率调度器
            scheduler.step(avg_val_loss)

            # 保存最佳模型
            if avg_val_loss < best_loss:
                best_loss = avg_val_loss
                torch.save(model.state_dict(), os.path.join(SAVE_DIR, "unet_best.pth"))
                print(f"   💾 保存最佳模型 (loss: {best_loss:.4f})")

            # 每5个epoch保存一次检查点
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

    # 保存最终模型
    torch.save(model.state_dict(), os.path.join(SAVE_DIR, "unet_final.pth"))
    print(f"\n🎉 训练完成！")
    print(f"📁 模型保存路径：{SAVE_DIR}")

if __name__ == '__main__':
    main()