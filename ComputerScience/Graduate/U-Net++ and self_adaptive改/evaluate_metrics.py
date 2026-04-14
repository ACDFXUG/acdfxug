import os
import torch
from PIL import Image
import torchvision.transforms as transforms
from tqdm import tqdm
from piq import psnr, ssim
import lpips

# 导入之前的模块
from dataset import MedicalRestorationDataset, transform
from unet_plusplus_adaptive import UNetPlusPlusAdaptive
from degradation_simulator import apply_realistic_degradation

# ==================== 配置区 ====================
TEST_DATA_ROOT = '../MRI-Images-of-Brain-Tumor/timri/test'
MODEL_PATH = './checkpoints/unet_final.pth'  # 确保这里指向你训练好的模型
BATCH_SIZE = 4
DEVICE = torch.device("cuda" if torch.cuda.is_available() else "cpu")

def main():
    print(f"🚀 正在加载模型与数据... (设备: {DEVICE})")

    # 1. 加载模型
    model = UNetPlusPlusAdaptive().to(DEVICE)
    try:
        model.load_state_dict(torch.load(MODEL_PATH, map_location=DEVICE))
        print(f"✅ 模型加载成功: {MODEL_PATH}")
    except FileNotFoundError:
        print(f"❌ 错误：找不到模型文件 {MODEL_PATH}，请先运行 train.py")
        return
    
    model.eval()

    # 2. 加载测试数据集
    # 注意：这里我们只取 clean image，degraded image 会在代码中动态生成以保证公平
    test_dataset = MedicalRestorationDataset(TEST_DATA_ROOT, transform=transform)
    test_loader = torch.utils.data.DataLoader(test_dataset, batch_size=BATCH_SIZE, shuffle=False, num_workers=0)

    # 3. 初始化 LPIPS 模型 (使用 alex 网络，速度快)
    loss_fn_alex = lpips.LPIPS(net='alex').to(DEVICE)

    # 累积指标
    total_psnr = 0.0
    total_ssim = 0.0
    total_lpips = 0.0
    count = 0

    print(f"📊 开始评估 {len(test_dataset)} 张测试图像...")

    with torch.no_grad():
        pbar = tqdm(test_loader, desc="Evaluating")
        for clean_imgs, _ in pbar:
            clean_imgs = clean_imgs.to(DEVICE)  # [B, 1, 224, 224], range [-1, 1]
            
            # A. 生成退化图像 (必须和训练时的策略一致)
            degraded_imgs = apply_realistic_degradation(clean_imgs, deg_type='mixed',severity='heavy')
            
            # B. 模型推理
            restored_imgs = model(degraded_imgs)
            
            # C. 计算指标
            # PSNR 和 SSIM 需要输入范围是 [0, 1] 或 [0, 255]，piq 默认支持 float [0, 1]
            # 我们的数据是 [-1, 1]，需要转换一下
            clean_norm = (clean_imgs + 1) / 2.0
            restored_norm = (restored_imgs + 1) / 2.0
            restored_norm = torch.clamp(restored_norm, 0, 1) # 防止溢出

            # 计算 PSNR (batch 平均值)
            batch_psnr = psnr(restored_norm, clean_norm, data_range=1.0)
            
            # 计算 SSIM (batch 平均值)
            batch_ssim = ssim(restored_norm, clean_norm, data_range=1.0)
            
            # 计算 LPIPS (值越小越好)
            batch_lpips = loss_fn_alex(restored_imgs, clean_imgs).mean() # LPIPS 可以直接用 [-1, 1]

            # 累加
            total_psnr += batch_psnr.item() * clean_imgs.size(0)
            total_ssim += batch_ssim.item() * clean_imgs.size(0)
            total_lpips += batch_lpips.item() * clean_imgs.size(0)
            count += clean_imgs.size(0)

            pbar.set_postfix({
                "PSNR": f"{batch_psnr.item():.2f}", 
                "SSIM": f"{batch_ssim.item():.4f}", 
                "LPIPS": f"{batch_lpips.item():.4f}"
            })

    # ==================== 最终结果 ====================
    avg_psnr = total_psnr / count
    avg_ssim = total_ssim / count
    avg_lpips = total_lpips / count

    print("\n" + "="*40)
    print(f"📈 测试集评估结果 (共 {count} 张图)")
    print("="*40)
    print(f"🔹 PSNR (越高越好):  {avg_psnr:.4f} dB")
    print(f"🔹 SSIM (越高越好):  {avg_ssim:.4f}")
    print(f"🔹 LPIPS (越低越好): {avg_lpips:.4f}")
    print("="*40)
    
    # 💡 建议：将这些数据记录到你的 experiment_log.md 中
    print("💡 提示：请将上述数据记录到实验日志中，作为 Baseline 的对照数据。")

if __name__ == '__main__':
    main()