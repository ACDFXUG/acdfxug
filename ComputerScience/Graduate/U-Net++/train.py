import os
# 解决 Windows OMP 报错
os.environ["KMP_DUPLICATE_LIB_OK"] = "TRUE"

import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader
from tqdm import tqdm
import lpips
from piq import SSIMLoss  # 👈 引入 SSIM Loss (注意：这是 loss 函数，值越小越好)

# 导入你之前写的模块
from dataset import MedicalRestorationDataset, transform
from unetpp import UNetPlusPlus
from degradation_simulator import apply_realistic_degradation

# ==================== 配置区 ====================
DATA_ROOT = '../MRI-Images-of-Brain-Tumor/timri/train'
BATCH_SIZE = 8
NUM_EPOCHS = 30  
LEARNING_RATE = 1e-4
DEVICE = torch.device("cuda" if torch.cuda.is_available() else "cpu")
SAVE_DIR = "./checkpoints"
NUM_WORKERS = 4

# ⚖️ 损失函数权重配置 (经验值，可根据效果微调)
LAMBDA_MSE = 1.0    
LAMBDA_LPIPS = 0.1
LAMBDA_SSIM = 0.01

def main():
    os.makedirs(SAVE_DIR, exist_ok=True)

    print(f"🚀 使用设备：{DEVICE}")
    print(f"⚖️ 损失策略：MSE (λ={LAMBDA_MSE}) + LPIPS (λ={LAMBDA_LPIPS}) + SSIM (λ={LAMBDA_SSIM})")

    # 数据集
    dataset = MedicalRestorationDataset(DATA_ROOT, transform=transform)
    train_loader = DataLoader(
        dataset, 
        batch_size=BATCH_SIZE, 
        shuffle=True, 
        num_workers=NUM_WORKERS, 
        pin_memory=True if DEVICE.type == 'cuda' else False
    )

    # 模型
    model = UNetPlusPlus().to(DEVICE)
    
    # --- 初始化三个损失函数 ---
    criterion_mse = nn.MSELoss()
    
    # LPIPS (输入范围 [-1, 1])
    loss_fn_lpips = lpips.LPIPS(net='alex', version='0.1').to(DEVICE)
    loss_fn_lpips.eval() 

    # SSIM Loss (输入范围 [0, 1] 或 [0, 255]，piq 默认支持 float [0, 1])
    # data_range=1.0 表示输入数据范围是 0~1
    # reduction='mean' 返回标量
    criterion_ssim=SSIMLoss(data_range=1.0, reduction='mean')
    
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
        
        pbar = tqdm(train_loader, desc=f"Epoch {epoch+1}/{NUM_EPOCHS}")

        for clean_imgs, _ in pbar:
            clean_imgs = clean_imgs.to(DEVICE)  # [B, 1, 224, 224], range [-1, 1]

            # 动态退化
            degraded_imgs = apply_realistic_degradation(clean_imgs, deg_mode='mixed')

            # 前向传播
            restored_imgs = model(degraded_imgs)
            
            # --- 计算三重损失 ---
            
            # 1. MSE Loss
            mse_loss = criterion_mse(restored_imgs, clean_imgs)
            
            # 2. LPIPS Loss (直接用 [-1, 1] 数据)
            lpips_loss = loss_fn_lpips(restored_imgs, clean_imgs).mean()
            
            # 3. SSIM Loss (⚠️ 需要转换到 [0, 1] 范围)
            # piq 的 ssim_loss 期望输入在 [0, 1] 之间
            restored_norm = (restored_imgs + 1) / 2.0
            clean_norm = (clean_imgs + 1) / 2.0
            # 防止数值溢出导致 SSIM 计算错误
            restored_norm = torch.clamp(restored_norm, 0, 1)
            clean_norm = torch.clamp(clean_norm, 0, 1)
            
            ssim_loss_val = criterion_ssim(restored_norm, clean_norm)
            # 注意：ssim_loss 返回的是 (1 - SSIM)，所以值越小代表 SSIM 越高，符合 loss 定义

            # 总损失
            loss = (LAMBDA_MSE * mse_loss) + \
                   (LAMBDA_LPIPS * lpips_loss) + \
                   (LAMBDA_SSIM * ssim_loss_val)

            # 反向传播
            optimizer.zero_grad()
            loss.backward()
            optimizer.step()

            # 记录日志
            total_loss += loss.item()
            total_mse += mse_loss.item()
            total_lpips += lpips_loss.item()
            total_ssim += ssim_loss_val.item()
            
            pbar.set_postfix({
                "Total": f"{loss.item():.4f}", 
                "MSE": f"{mse_loss.item():.4f}", 
                "LPIPS": f"{lpips_loss.item():.4f}",
                "SSIM_L": f"{ssim_loss_val.item():.4f}" # SSIM Loss 值 (越小越好)
            })

        avg_loss = total_loss / len(train_loader)
        avg_ssim_loss = total_ssim / len(train_loader)
        
        print(f"\n✅ Epoch {epoch+1} 平均 Loss: {avg_loss:.4f} (SSIM_Loss: {avg_ssim_loss:.4f})")

        scheduler.step(avg_loss)

        # 保存最佳模型
        if avg_loss < best_loss:
            best_loss = avg_loss
            best_path = os.path.join(SAVE_DIR, "unet_best.pth")
            torch.save(model.state_dict(), best_path)
            print(f"🌟 发现更优模型，已保存至：{best_path}")

        if (epoch + 1) % 5 == 0:
            save_path = os.path.join(SAVE_DIR, f"unet_epoch_{epoch+1}.pth")
            torch.save(model.state_dict(), save_path)
            print(f"💾 检查点已保存至：{save_path}")

    final_path = os.path.join(SAVE_DIR, "unet_final.pth")
    torch.save(model.state_dict(), final_path)
    print(f"🎉 训练完成！最终模型已保存至：{final_path}")

if __name__ == '__main__':
    main()