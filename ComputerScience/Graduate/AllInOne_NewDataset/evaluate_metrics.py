import os
import torch
from tqdm import tqdm
from piq import psnr, ssim
import lpips

# 导入之前的模块
from dataset import MedicalRestorationDataset, get_data_loaders
from unet_plusplus_adaptive import UNetPlusPlusAdaptive
from MPRNet import MPRNet

# ==================== 配置区 ====================
BASE_DATA_ROOT = '../LoDoPaB-CT'  # LoDoPaB-CT数据集根目录
TEST_SIZE = 10  # 使用前10个hdf5文件进行测试
MODEL_PATH = './checkpoints/unet_final.pth'  # 确保这里指向你训练好的模型
BATCH_SIZE = 8  # 根据内存情况调整
DEVICE = torch.device("cuda" if torch.cuda.is_available() else "cpu")
MODEL_TYPE = 'Ours'  # 可选 'Ours' 或 'MPRNet'

def main():
    print(f"🚀 正在加载模型与数据... (设备: {DEVICE})")

    # 1. 加载模型
    if MODEL_TYPE == 'Ours':
        model = UNetPlusPlusAdaptive().to(DEVICE)
        print(f"✅ 已选择模型: UNet++ 自适应模型")
    elif MODEL_TYPE == 'MPRNet':
        model = MPRNet(in_c=1, out_c=1, n_feat=20, scale_unetfeats=12, scale_orsnetfeats=8, num_cab=4).to(DEVICE)
        print(f"✅ 已选择模型: MPRNet")
    else:
        raise ValueError(f"❌ 未知的模型类型: {MODEL_TYPE}")
    
    try:
        model.load_state_dict(torch.load(MODEL_PATH, map_location=DEVICE))
        print(f"✅ 模型加载成功: {MODEL_PATH}")
    except FileNotFoundError:
        print(f"❌ 错误：找不到模型文件 {MODEL_PATH}，请先运行 train.py")
        return
    
    model.eval()

    # 2. 加载测试数据集（使用独立的测试集）
    _, _, test_loader = get_data_loaders(
        BASE_DATA_ROOT,
        train_size=1,  # 不需要训练数据
        val_size=1,    # 不需要验证数据
        test_size=TEST_SIZE,  # 使用TEST_SIZE个文件作为测试集
        batch_size=BATCH_SIZE
    )

    # 3. 初始化 LPIPS 模型 (使用 alex 网络，速度快)
    loss_fn_alex = lpips.LPIPS(net='alex').to(DEVICE)

    # 累积指标
    total_psnr = 0.0
    total_ssim = 0.0
    total_lpips = 0.0
    count = 0

    print(f"📊 开始评估...")

    with torch.no_grad():
        pbar = tqdm(test_loader, desc="Evaluating")
        for inputs, targets in pbar:  # 直接获取输入和目标
            inputs = inputs.to(DEVICE)  # [B, 1, 362, 362], range [-1, 1]
            targets = targets.to(DEVICE)  # [B, 1, 362, 362], range [-1, 1]
            
            # A. 直接使用输入作为退化图像（真实数据集，不是模拟的）
            degraded_imgs = inputs
            
            # B. 模型推理
            restored_imgs = model(degraded_imgs)
            
            # C. 计算指标
            # PSNR 和 SSIM 需要输入范围是 [0, 1] 或 [0, 255]，piq 默认支持 float [0, 1]
            # 我们的数据是 [-1, 1]，需要转换一下
            targets_norm = (targets + 1) / 2.0
            restored_norm = (restored_imgs + 1) / 2.0
            targets_norm = torch.clamp(targets_norm, 0, 1) # 防止溢出
            restored_norm = torch.clamp(restored_norm, 0, 1) # 防止溢出

            # 计算 PSNR (batch 平均值)
            batch_psnr = psnr(restored_norm, targets_norm, data_range=1.0)
            
            # 计算 SSIM (batch 平均值)
            batch_ssim = ssim(restored_norm, targets_norm, data_range=1.0)
            
            # 计算 LPIPS (值越小越好)
            batch_lpips = loss_fn_alex(restored_imgs, targets).mean()
            
            # 累积指标
            total_psnr += batch_psnr.item()
            total_ssim += batch_ssim.item()
            total_lpips += batch_lpips.item()
            count += 1

            # 更新进度条
            pbar.set_postfix({
                "PSNR": f"{batch_psnr.item():.2f}",
                "SSIM": f"{batch_ssim.item():.3f}",
                "LPIPS": f"{batch_lpips.item():.3f}"
            })

    # 计算平均指标
    avg_psnr = total_psnr / count
    avg_ssim = total_ssim / count
    avg_lpips = total_lpips / count

    print(f"\n📈 最终评估结果:")
    print(f"   PSNR: {avg_psnr:.2f} dB")
    print(f"   SSIM: {avg_ssim:.3f}")
    print(f"   LPIPS: {avg_lpips:.3f}")

if __name__ == "__main__":
    main()