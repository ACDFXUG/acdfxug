import os
os.environ["KMP_DUPLICATE_LIB_OK"] = "TRUE"
import cv2
import torch
import torch.nn.functional as F
import random
import matplotlib.pyplot as plt
from dataset import MedicalRestorationDataset,transform

def apply_realistic_degradation(image, deg_type='mixed', severity='medium'):
    """
    image: [B, C, H, W] tensor, range [-1, 1]
    """
    degraded = image.clone()
    B, C, H, W = image.shape  # ✅ 在这里获取所有维度
    
    # 根据严重程度设置参数范围
    if severity == 'light':
        gauss_std = 0.01
        blur_sigma = 0.5
        gamma_range = (0.85, 0.95)
    elif severity == 'medium':
        gauss_std = 0.03
        blur_sigma = 1.0
        gamma_range = (0.7, 0.85)
    else:  # heavy
        gauss_std = 0.05
        blur_sigma = 2.0
        gamma_range = (0.5, 0.7)

    if deg_type == 'noise' or deg_type == 'mixed':
        img_pos = (degraded + 1) / 2
        
        # 信号相关噪声
        signal_std = torch.sqrt(torch.clamp(img_pos, 1e-7, 1.0)) * gauss_std
        noise = torch.randn_like(img_pos) * signal_std
        noise += torch.randn_like(img_pos) * gauss_std * 0.5
        
        noisy_pos = torch.clamp(img_pos + noise, 0, 1)
        degraded = noisy_pos * 2 - 1

    if deg_type == 'blur' or deg_type == 'mixed':
        if blur_sigma > 0.3:
            kernel_size = int(6 * blur_sigma) | 1  # 确保奇数
            if kernel_size >= 3:
                # 创建2D高斯核
                x = torch.arange(kernel_size, dtype=torch.float32, device=image.device) - kernel_size // 2
                gauss_kernel = torch.exp(-(x ** 2) / (2 * blur_sigma ** 2))
                gauss_kernel = gauss_kernel / gauss_kernel.sum()
                gauss_kernel_2d = gauss_kernel.unsqueeze(0) * gauss_kernel.unsqueeze(1)
                
                # ✅ 修复：正确的维度扩展 [C, 1, k, k] 用于 groups=C
                gauss_kernel_2d = gauss_kernel_2d.unsqueeze(0).unsqueeze(0)  # [1, 1, k, k]
                gauss_kernel_2d = gauss_kernel_2d.repeat(C, 1, 1, 1)  # [C, 1, k, k]
                
                # 使用replicate填充避免边界问题，groups=C 表示每个通道独立卷积
                degraded = F.conv2d(
                    F.pad(degraded, (kernel_size//2,)*4, mode='replicate'), 
                    gauss_kernel_2d, 
                    padding=0, 
                    groups=C
                )

    if deg_type == 'low_light' or deg_type == 'mixed':
        gamma = random.uniform(*gamma_range)
        img_01 = (degraded + 1) / 2
        img_01 = torch.clamp(img_01, 1e-7, 1.0)
        img_01 = img_01 ** gamma
        degraded = img_01 * 2 - 1

    return torch.clamp(degraded, -1, 1)

if __name__ == '__main__':
    test_dataset = MedicalRestorationDataset('../MRI-Images-of-Brain-Tumor/timri/test', transform=transform)
    test_loader = torch.utils.data.DataLoader(test_dataset, batch_size=1, shuffle=False)
    clean,_= next(iter(test_loader))
    degraded = apply_realistic_degradation(clean, deg_type='mixed', severity='heavy')
    plt.imshow(clean[0,0].cpu(),cmap='gray')
    plt.show()
    plt.imshow(degraded[0,0].cpu(),cmap='gray')
    plt.show()

    