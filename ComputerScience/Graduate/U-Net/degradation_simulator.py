import torch
import torch.nn.functional as F
import random

def apply_realistic_degradation(clean_img, deg_mode='mixed'):
    """
    clean_img: [B, 1, 224, 224], range [-1, 1]
    返回 degraded_img: [B, 1, 224, 224]
    """
    degraded = clean_img.clone()
    B, C, H, W = degraded.shape
    
    # 1. 噪声模拟 (泊松 + 高斯)
    if deg_mode in ['noise', 'mixed']:
        # 将 [-1, 1] 临时转回 [0, 1] 以便加泊松噪声
        img_pos = (degraded + 1) / 2 
        # 泊松噪声强度因子 (医学图像低剂量模拟)
        factor = random.uniform(20, 60) 
        noisy_pos = torch.poisson(img_pos * factor) / factor
        # 加一点高斯噪声
        gauss_noise = torch.randn_like(noisy_pos) * random.uniform(0.01, 0.05)
        noisy_pos = noisy_pos + gauss_noise
        noisy_pos = torch.clamp(noisy_pos, 0, 1)
        degraded = (noisy_pos * 2) - 1 # 转回 [-1, 1]

    # 2. 模糊模拟 (运动模糊/高斯模糊)
    # 注意：224x224 图像，kernel_size 设为 3 或 5 即可，不要太大
    if deg_mode in ['blur', 'mixed']:
        k_size = random.choice([3, 5]) 
        # 使用平均池化近似模糊 (简单高效)
        degraded = F.avg_pool2d(degraded, kernel_size=k_size, stride=1, padding=k_size//2)

    # 3. 亮度/对比度退化 (欠曝光模拟)
    if deg_mode in ['low_light', 'mixed']:
        gamma = random.uniform(0.6, 0.9) # 让图像变暗
        # 注意 sign 保持符号，只对绝对值做 gamma
        degraded = torch.sign(degraded) * (torch.abs(degraded) ** gamma)

    # 截断防止溢出
    degraded = torch.clamp(degraded, -1, 1)
    
    return degraded

# 测试
if __name__ == "__main__":
    dummy = torch.rand(1, 1, 224, 224) * 2 - 1
    out = apply_realistic_degradation(dummy, 'mixed')
    print("Degradation applied successfully.")