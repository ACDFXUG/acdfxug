import os
# ⚠️ 必须在导入 torch 之前设置环境变量，解决 Windows OMP 报错
os.environ["KMP_DUPLICATE_LIB_OK"] = "TRUE"

import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader
from tqdm import tqdm
import lpips
from piq import SSIMLoss

# 导入你之前写的模块
from dataset import MedicalRestorationDataset, transform
from unet_plusplus_adaptive import UNetPlusPlusAdaptive 
from degradation_simulator import apply_realistic_degradation

# ==================== 配置区 ====================
DATA_ROOT = '../MRI-Images-of-Brain-Tumor/timri/train'
BATCH_SIZE = 8
NUM_EPOCHS = 50  # 增加 Epoch，因为模型更复杂，需要更多时间收敛
LEARNING_RATE = 1e-4
DEVICE = torch.device("cuda" if torch.cuda.is_available() else "cpu")
SAVE_DIR = "./checkpoints" 
os.makedirs(SAVE_DIR, exist_ok=True)

NUM_WORKERS = 4 

# ⚖️ 损失函数权重配置 (四重损失)
LAMBDA_MSE = 1.0      
LAMBDA_LPIPS = 0.1    
LAMBDA_SSIM = 0.02    # 稍微调大一点，配合自适应模块应该能更好收敛
LAMBDA_FFT = 0.00005    # FFT Loss 权重通常较小

# ==================== 辅助函数 ====================
def fft_loss(pred, target):
    """
    计算频域损失 (幅度谱差异)
    输入范围：[-1, 1]
    """
    # 2D FFT
    pred_fft = torch.fft.fft2(pred)
    target_fft = torch.fft.fft2(target)
    
    # 计算幅度谱
    pred_mag = torch.abs(pred_fft)
    target_mag = torch.abs(target_fft)
    
    # 返回均方误差
    return torch.mean((pred_mag - target_mag) ** 2)

def main():
    print(f"🚀 使用设备：{DEVICE}")
    print(f"📂 数据路径：{DATA_ROOT}")
    print(f"🏗️ 模型架构：UNetPlusPlusAdaptive (带退化估计 + AdaIN)")
    print(f"⚖️ 损失策略：MSE ({LAMBDA_MSE}) + LPIPS ({LAMBDA_LPIPS}) + SSIM ({LAMBDA_SSIM}) + FFT ({LAMBDA_FFT})")

    # 初始化数据集和 DataLoader
    dataset = MedicalRestorationDataset(DATA_ROOT, transform=transform)
    train_loader = DataLoader(
        dataset, 
        batch_size=BATCH_SIZE, 
        shuffle=True, 
        num_workers=NUM_WORKERS, 
        pin_memory=True if DEVICE.type == 'cuda' else False
    )

    # 初始化模型 (自适应版本)
    model = UNetPlusPlusAdaptive().to(DEVICE)
    
    # 定义损失函数
    criterion_mse = nn.MSELoss()
    
    # LPIPS (输入范围 [-1, 1])
    loss_fn_lpips = lpips.LPIPS(net='alex', version='0.1').to(DEVICE)
    loss_fn_lpips.eval() 

    # SSIM Loss (输入范围 [0, 1])
    criterion_ssim = SSIMLoss(data_range=1.0, reduction='mean')
    
    # 优化器
    optimizer = optim.Adam(model.parameters(), lr=LEARNING_RATE)
    scheduler = optim.lr_scheduler.ReduceLROnPlateau(optimizer, mode='min', factor=0.5, patience=5)

    print(f"📦 数据集大小：{len(dataset)} 张图像")
    print(f"🏃 开始训练... (共 {NUM_EPOCHS} 个 Epoch)")

    best_loss = float('inf')

    for epoch in range(NUM_EPOCHS):
        model.train()
        total_loss = 0.0
        total_mse = 0.0
        total_lpips = 0.0
        total_ssim = 0.0
        total_fft = 0.0
        
        pbar = tqdm(train_loader, desc=f"Epoch {epoch+1}/{NUM_EPOCHS}")

        if epoch < 20:
            current_severity = 'light'      # 前 20 轮：轻度退化
        # elif epoch < 35:
        #     current_severity = 'medium'     # 中间：中度退化
        # else:
        #     current_severity = 'heavy'      # 最后阶段：重度退化 (挑战极限)
        else:
            current_severity='medium'

        for clean_imgs, _ in pbar:
            clean_imgs = clean_imgs.to(DEVICE)  # [B, 1, 224, 224], range [-1, 1]

            # 👇 动态生成退化图像
            degraded_imgs = apply_realistic_degradation(clean_imgs, deg_type='mixed',severity=current_severity)

            # 前向传播 (模型内部会自动进行退化估计和自适应调制)
            restored_imgs = model(degraded_imgs)
            
            # --- 计算四重损失 ---
            
            # 1. MSE Loss
            mse_loss = criterion_mse(restored_imgs, clean_imgs)
            
            # 2. LPIPS Loss
            lpips_loss = loss_fn_lpips(restored_imgs, clean_imgs).mean()
            
            # 3. SSIM Loss (需转换到 [0, 1])
            restored_norm = torch.clamp((restored_imgs + 1) / 2.0, 0, 1)
            clean_norm = torch.clamp((clean_imgs + 1) / 2.0, 0, 1)
            ssim_loss_val = criterion_ssim(restored_norm, clean_norm)
            
            # 4. FFT Loss (直接用 [-1, 1])
            fft_loss_val = fft_loss(restored_imgs, clean_imgs)

            # 总损失
            loss = (LAMBDA_MSE * mse_loss) + \
                   (LAMBDA_LPIPS * lpips_loss) + \
                   (LAMBDA_SSIM * ssim_loss_val) + \
                   (LAMBDA_FFT * fft_loss_val)

            # 反向传播
            optimizer.zero_grad()
            loss.backward()
            
            # 梯度裁剪 (防止 GAN 类结构或复杂网络梯度爆炸，可选但推荐)
            torch.nn.utils.clip_grad_norm_(model.parameters(), max_norm=1.0)
            
            optimizer.step()

            # 记录日志
            total_loss += loss.item()
            total_mse += mse_loss.item()
            total_lpips += lpips_loss.item()
            total_ssim += ssim_loss_val.item()
            total_fft += fft_loss_val.item()
            
            pbar.set_postfix({
                "Total": f"{loss.item():.4f}", 
                "MSE": f"{mse_loss.item():.4f}", 
                "LPIPS": f"{lpips_loss.item():.4f}",
                "SSIM_L": f"{ssim_loss_val.item():.4f}",
                "FFT_L": f"{fft_loss_val.item():.4f}"
            })

        avg_loss = total_loss / len(train_loader)
        avg_ssim_loss = total_ssim / len(train_loader)
        
        print(f"\n✅ Epoch {epoch+1} 平均 Loss: {avg_loss:.4f} (SSIM_Loss: {avg_ssim_loss:.4f})")

        scheduler.step(avg_loss)

        # 💾 保存最佳模型
        if avg_loss < best_loss:
            best_loss = avg_loss
            best_path = os.path.join(SAVE_DIR, "unet_best.pth")
            torch.save(model.state_dict(), best_path)
            print(f"🌟 发现更优模型，已保存至：{best_path}")

        # 💾 每 5 个 epoch 保存一次检查点
        if (epoch + 1) % 5 == 0:
            save_path = os.path.join(SAVE_DIR, f"unet_epoch_{epoch+1}.pth")
            torch.save(model.state_dict(), save_path)
            print(f"💾 检查点已保存至：{save_path}")

    # 💾 保存最终模型
    final_path = os.path.join(SAVE_DIR, "unet_final.pth")
    torch.save(model.state_dict(), final_path)
    print(f"🎉 训练完成！最终模型已保存至：{final_path}")

if __name__ == '__main__':
    main()