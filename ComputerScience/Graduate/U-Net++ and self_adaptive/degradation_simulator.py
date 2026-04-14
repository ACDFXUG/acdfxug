import torch
import torch.nn.functional as F
import random

def apply_realistic_degradation(image, deg_type='mixed', severity='medium'):
    """
    image: [B, C, H, W] tensor, range [-1, 1]
    deg_type: 'noise', 'blur', 'low_light', 'mixed'
    severity: 'light', 'medium', 'heavy' (控制整体退化强度)
    """
    degraded = image.clone()
    
    # 根据严重程度设置参数范围
    if severity == 'light':
        poisson_range = (150, 300)
        gauss_range = (0.002, 0.01)
        blur_kernels = [3, 3, 3, 5] # 大部分是轻微模糊
        gamma_range = (0.8, 0.95)
    elif severity == 'medium':
        poisson_range = (80, 150)
        gauss_range = (0.005, 0.02)
        blur_kernels = [3, 3, 5, 5]
        gamma_range = (0.6, 0.85)
    else: # heavy (之前的设置)
        poisson_range = (20, 60)
        gauss_range = (0.01, 0.05)
        blur_kernels = [3, 5, 7, 9]
        gamma_range = (0.4, 0.7)

    if deg_type == 'noise' or deg_type == 'mixed':
        # 1. 泊松噪声模拟 (低剂量 CT/MRI)
        # factor 越大，噪声越小
        factor = random.uniform(*poisson_range)
        
        # 将 [-1, 1] 转回 [0, 1] 以便加泊松噪声
        img_pos = (degraded + 1) / 2 
        # 防止数值过小导致泊松采样错误
        img_pos = torch.clamp(img_pos, 1e-6, 1.0) 
        
        noisy_pos = torch.poisson(img_pos * factor) / factor
        
        # 2. 叠加少量高斯噪声
        gauss_noise = torch.randn_like(noisy_pos) * random.uniform(*gauss_range)
        noisy_pos = noisy_pos + gauss_noise
        
        noisy_pos = torch.clamp(noisy_pos, 0, 1)
        degraded = (noisy_pos * 2) - 1 # 转回 [-1, 1]

    if deg_type == 'blur' or deg_type == 'mixed':
        # 运动模糊/高斯模糊
        k_size = random.choice(blur_kernels)
        # 使用平均池化近似模糊
        degraded = F.avg_pool2d(degraded, kernel_size=k_size, stride=1, padding=k_size//2)

    if deg_type == 'low_light' or deg_type == 'mixed':
        # 亮度/对比度退化
        gamma = random.uniform(*gamma_range)
        degraded = torch.sign(degraded) * (torch.abs(degraded) ** gamma)

    # 截断防止溢出
    degraded = torch.clamp(degraded, -1, 1)
    
    return degraded