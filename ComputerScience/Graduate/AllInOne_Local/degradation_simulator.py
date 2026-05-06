import os
os.environ["KMP_DUPLICATE_LIB_OK"] = "TRUE"
import torch
import torch.nn.functional as F
import random
import matplotlib.pyplot as plt


def apply_realistic_degradation(image, deg_type='mixed', severity='medium', 
                                 spatial_degradation=False, return_mask=False):
    """
    医学图像退化模拟函数
    
    Args:
        image: [B, C, H, W] tensor, range [-1, 1]
        deg_type: 'noise', 'blur', 'low_light', 'mixed'
        severity: 'light', 'medium', 'heavy'
        spatial_degradation: 是否使用空间自适应退化
        return_mask: 是否返回退化强度图（仅当spatial_degradation=True时有效）
    
    Returns:
        degraded: [B, C, H, W] tensor, range [-1, 1]
        intensity_map (可选): 当spatial_degradation=True且return_mask=True时返回强度图
    """
    
    if not spatial_degradation:
        return _apply_global_degradation(image, deg_type, severity, return_mask)
    else:
        return _apply_spatial_degradation_composite(image, deg_type, severity, return_mask)


def _apply_global_degradation(image, deg_type='mixed', severity='medium', return_mask=False):
    """
    全局退化（原始逻辑）
    所有像素使用相同的退化参数，mixed模式是顺序叠加
    """
    degraded = image.clone()
    B, C, H, W = image.shape
    
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

    # mixed模式：顺序叠加所有退化
    if deg_type == 'noise' or deg_type == 'mixed':
        img_pos = (degraded + 1) / 2
        signal_std = torch.sqrt(torch.clamp(img_pos, 1e-7, 1.0)) * gauss_std
        noise = torch.randn_like(img_pos) * signal_std
        noise += torch.randn_like(img_pos) * gauss_std * 0.5
        noisy_pos = torch.clamp(img_pos + noise, 0, 1)
        degraded = noisy_pos * 2 - 1

    if deg_type == 'blur' or deg_type == 'mixed':
        if blur_sigma > 0.3:
            degraded = _apply_gaussian_blur(degraded, blur_sigma)

    if deg_type == 'low_light' or deg_type == 'mixed':
        gamma = random.uniform(*gamma_range)
        img_01 = (degraded + 1) / 2
        img_01 = torch.clamp(img_01, 1e-7, 1.0)
        img_01 = img_01 ** gamma
        degraded = img_01 * 2 - 1

    if return_mask:
        mask = torch.ones(B, 3, H, W, device=image.device) / 3.0
        return torch.clamp(degraded, -1, 1), mask
    
    return torch.clamp(degraded, -1, 1)


def _apply_spatial_degradation_composite(image, deg_type='mixed', severity='medium', return_mask=False):
    """
    空间退化（叠加模式 - 与全局mixed逻辑一致）
    """
    B, C, H, W = image.shape
    
    # 根据严重程度设置参数范围
    if severity == 'light':
        noise_range = (0.005, 0.015)
        blur_range = (0.3, 0.8)
        gamma_range = (0.85, 0.95)
    elif severity == 'medium':
        noise_range = (0.025, 0.04)
        blur_range = (0.8, 1.2)
        gamma_range = (0.70, 0.85)
    else:  # heavy
        noise_range = (0.05, 0.10)
        blur_range = (1.7, 2.3)
        gamma_range = (0.50, 0.70)
    
    # ========== 混合退化：叠加模式 ==========
    if deg_type == 'mixed':
        # 为每种退化单独生成空间强度图 [B, 1, H, W]
        noise_intensity = _generate_spatial_intensity_map(B, H, W, noise_range, image.device)
        blur_intensity = _generate_spatial_intensity_map(B, H, W, blur_range, image.device)
        lowlight_intensity = _generate_spatial_intensity_map(B, H, W, gamma_range, image.device)
        
        # 调试：打印维度
        # print(f"noise_intensity shape: {noise_intensity.shape}")
        # print(f"blur_intensity shape: {blur_intensity.shape}")
        # print(f"lowlight_intensity shape: {lowlight_intensity.shape}")
        
        # 顺序应用退化
        degraded = image.clone()
        degraded = _apply_noise_spatial(degraded, noise_intensity)
        degraded = _apply_blur_spatial(degraded, blur_intensity)
        degraded = _apply_lowlight_spatial(degraded, lowlight_intensity)
        
        if return_mask:
            # 确保所有强度图都是 [B, 1, H, W]
            deg_mask = torch.cat([noise_intensity, blur_intensity, lowlight_intensity], dim=1)
            return torch.clamp(degraded, -1, 1), deg_mask
        
        return torch.clamp(degraded, -1, 1)
    
    # ========== 单一退化 ==========
    elif deg_type == 'noise':
        intensity_map = _generate_spatial_intensity_map(B, H, W, noise_range, image.device)
        degraded = _apply_noise_spatial(image, intensity_map)
        if return_mask:
            return torch.clamp(degraded, -1, 1), intensity_map
        return torch.clamp(degraded, -1, 1)
        
    elif deg_type == 'blur':
        intensity_map = _generate_spatial_intensity_map(B, H, W, blur_range, image.device)
        degraded = _apply_blur_spatial(image, intensity_map)
        if return_mask:
            return torch.clamp(degraded, -1, 1), intensity_map
        return torch.clamp(degraded, -1, 1)
        
    elif deg_type == 'low_light':
        intensity_map = _generate_spatial_intensity_map(B, H, W, gamma_range, image.device)
        degraded = _apply_lowlight_spatial(image, intensity_map)
        if return_mask:
            return torch.clamp(degraded, -1, 1), intensity_map
        return torch.clamp(degraded, -1, 1)


def _generate_spatial_intensity_map(B, H, W, intensity_range, device):
    """
    生成空间变化的强度图
    
    Returns:
        intensity_map: [B, 1, H, W] 每个像素的退化强度
    """
    min_val, max_val = intensity_range
    
    # 创建网格坐标（使用 meshgrid 确保维度正确）
    y_coords = torch.linspace(0, 1, H, device=device)
    x_coords = torch.linspace(0, 1, W, device=device)
    yy, xx = torch.meshgrid(y_coords, x_coords, indexing='ij')
    # yy, xx 都是 [H, W]
    
    intensity_maps = []
    
    for b in range(B):
        # 随机选择一种空间模式
        pattern = random.choice(['diagonal', 'radial', 'horizontal', 'vertical'])
        
        if pattern == 'diagonal':
            # 对角渐变：左上角强，右下角弱
            intensity = 1 - (xx + yy) / 2
        elif pattern == 'radial':
            # 径向渐变：中心强，边缘弱
            cx, cy = 0.5, 0.5
            r = torch.sqrt((xx - cx)**2 + (yy - cy)**2)
            intensity = 1 - r * 1.2
            intensity = torch.clamp(intensity, 0, 1)
        elif pattern == 'horizontal':
            # 水平渐变：左强右弱
            intensity = 1 - xx
        elif pattern == 'vertical':
            # 垂直渐变：上强下弱
            intensity = 1 - yy
        else:
            intensity = torch.ones(H, W, device=device) * 0.5
        
        # 添加 batch 和 channel 维度 [1, 1, H, W]
        intensity = intensity.unsqueeze(0).unsqueeze(0)
        
        # 缩放到指定范围
        intensity = min_val + intensity * (max_val - min_val)
        intensity_maps.append(intensity)
    
    # 合并batch维度 [B, 1, H, W]
    intensity_map = torch.cat(intensity_maps, dim=0)
    
    return intensity_map

def _apply_noise_spatial(image, intensity_map):
    """
    应用空间变化的噪声退化
    """
    B, C, H, W = image.shape
    
    # 确保 intensity_map 是 tensor 且维度正确
    if isinstance(intensity_map, (float, int)):
        intensity_map = torch.ones(B, 1, H, W, device=image.device) * intensity_map
    
    # 确保 intensity_map 是 [B, 1, H, W]
    if intensity_map.dim() == 3:
        intensity_map = intensity_map.unsqueeze(1)
    
    # 扩展到所有通道
    if intensity_map.shape[1] == 1 and C > 1:
        intensity_map = intensity_map.expand(-1, C, -1, -1)
    
    img_pos = (image + 1) / 2
    img_pos = torch.clamp(img_pos, 1e-7, 1.0)
    
    signal_std = torch.sqrt(img_pos) * intensity_map
    noise = torch.randn_like(img_pos) * signal_std
    noise += torch.randn_like(img_pos) * intensity_map * 0.5
    
    noisy_pos = torch.clamp(img_pos + noise, 0, 1)
    degraded = noisy_pos * 2 - 1
    
    return degraded


def _apply_blur_spatial(image, intensity_map):
    """
    应用空间变化的模糊退化
    """
    B, C, H, W = image.shape
    
    # 如果是 float，直接应用全局模糊
    if isinstance(intensity_map, (float, int)):
        if intensity_map > 0.3:
            return _apply_gaussian_blur(image, intensity_map)
        return image
    
    # 确保 intensity_map 是 [B, 1, H, W]
    if intensity_map.dim() == 3:
        intensity_map = intensity_map.unsqueeze(1)
    
    # 如果是 [B, C, H, W] 且 C > 1，取平均
    if intensity_map.shape[1] > 1:
        intensity_map = intensity_map.mean(dim=1, keepdim=True)
    
    # 获取强度范围
    min_intensity = intensity_map.min().item()
    max_intensity = intensity_map.max().item()
    
    if max_intensity - min_intensity < 0.1:
        sigma = intensity_map.mean().item()
        return _apply_gaussian_blur(image, sigma)
    
    sigma_light = max(0.3, min_intensity)
    sigma_heavy = max(sigma_light + 0.5, max_intensity)
    
    blurred_light = _apply_gaussian_blur(image, sigma_light)
    blurred_heavy = _apply_gaussian_blur(image, sigma_heavy)
    
    weight = (intensity_map - min_intensity) / (max_intensity - min_intensity + 1e-7)
    weight = torch.clamp(weight, 0, 1)
    
    if C > 1:
        weight = weight.expand(-1, C, -1, -1)
    
    degraded = (1 - weight) * blurred_light + weight * blurred_heavy
    
    return degraded


def _apply_lowlight_spatial(image, intensity_map):
    """
    应用空间变化的低光退化
    """
    B, C, H, W = image.shape
    
    # 如果是 float，转换为 tensor
    if isinstance(intensity_map, (float, int)):
        intensity_map = torch.ones(B, 1, H, W, device=image.device) * intensity_map
    
    # 确保 intensity_map 是 [B, 1, H, W]
    if intensity_map.dim() == 3:
        intensity_map = intensity_map.unsqueeze(1)
    
    if intensity_map.shape[1] > 1:
        intensity_map = intensity_map.mean(dim=1, keepdim=True)
    
    img_pos = (image + 1) / 2
    img_pos = torch.clamp(img_pos, 1e-7, 1.0)
    
    gamma_map = torch.clamp(intensity_map, 0.3, 1.5)
    
    if C > 1:
        gamma_map = gamma_map.expand(-1, C, -1, -1)
    
    gamma_mean = gamma_map.mean().item()
    gamma_corrected = img_pos ** gamma_mean
    
    correction = 1 + (gamma_map - gamma_mean) * 0.3
    correction = torch.clamp(correction, 0.7, 1.3)
    
    degraded_pos = torch.clamp(gamma_corrected * correction, 0, 1)
    degraded = degraded_pos * 2 - 1
    
    return degraded


def _apply_gaussian_blur(image, sigma):
    """应用高斯模糊"""
    if sigma < 0.3:
        return image
    
    B, C, H, W = image.shape
    kernel_size = int(6 * sigma) | 1
    if kernel_size < 3:
        return image
    
    x = torch.arange(kernel_size, dtype=torch.float32, device=image.device) - kernel_size // 2
    gauss_kernel = torch.exp(-(x ** 2) / (2 * sigma ** 2))
    gauss_kernel = gauss_kernel / gauss_kernel.sum()
    gauss_kernel_2d = gauss_kernel.unsqueeze(0) * gauss_kernel.unsqueeze(1)
    gauss_kernel_2d = gauss_kernel_2d.unsqueeze(0).unsqueeze(0).repeat(C, 1, 1, 1)
    
    pad = kernel_size // 2
    blurred = F.conv2d(F.pad(image, (pad, pad, pad, pad), mode='replicate'), 
                        gauss_kernel_2d, padding=0, groups=C)
    return blurred


# ==================== 测试代码 ====================
if __name__ == '__main__':
    from dataset import MedicalRestorationDataset, transform
    
    test_dataset = MedicalRestorationDataset('../MRI-Images-of-Brain-Tumor/timri/test', transform=transform)
    test_loader = torch.utils.data.DataLoader(test_dataset, batch_size=1, shuffle=False)
    clean, _ = next(iter(test_loader))
    
    print("=" * 60)
    print("退化模拟器测试（叠加模式）")
    print("=" * 60)
    
    # 测试1: 全局退化 - mixed
    print("\n📊 测试1: 全局退化 - mixed")
    degraded_global = apply_realistic_degradation(clean, deg_type='mixed', severity='medium')
    print(f"  输出范围: [{degraded_global.min().item():.2f}, {degraded_global.max().item():.2f}]")
    
    # 测试2: 空间退化 - mixed（叠加模式）
    print("\n📊 测试2: 空间退化 - mixed（叠加模式）")
    degraded_spatial_mixed, intensity_map = apply_realistic_degradation(
        clean, deg_type='mixed', severity='medium', 
        spatial_degradation=True, return_mask=True
    )
    print(f"  输出范围: [{degraded_spatial_mixed.min().item():.2f}, {degraded_spatial_mixed.max().item():.2f}]")
    print(f"  噪声强度范围: [{intensity_map[0,0].min().item():.3f}, {intensity_map[0,0].max().item():.3f}]")
    print(f"  模糊强度范围: [{intensity_map[0,1].min().item():.3f}, {intensity_map[0,1].max().item():.3f}]")
    print(f"  低光强度范围: [{intensity_map[0,2].min().item():.3f}, {intensity_map[0,2].max().item():.3f}]")
    
    # 测试3: 空间退化 - noise
    print("\n📊 测试3: 空间退化 - noise")
    degraded_spatial_noise = apply_realistic_degradation(
        clean, deg_type='noise', severity='medium', 
        spatial_degradation=True
    )
    print(f"  输出范围: [{degraded_spatial_noise.min().item():.2f}, {degraded_spatial_noise.max().item():.2f}]")
    
    # 测试4: 空间退化 - blur
    print("\n📊 测试4: 空间退化 - blur")
    degraded_spatial_blur = apply_realistic_degradation(
        clean, deg_type='blur', severity='medium', 
        spatial_degradation=True
    )
    print(f"  输出范围: [{degraded_spatial_blur.min().item():.2f}, {degraded_spatial_blur.max().item():.2f}]")
    
    # 测试5: 空间退化 - low_light
    print("\n📊 测试5: 空间退化 - low_light")
    degraded_spatial_lowlight = apply_realistic_degradation(
        clean, deg_type='low_light', severity='medium', 
        spatial_degradation=True
    )
    print(f"  输出范围: [{degraded_spatial_lowlight.min().item():.2f}, {degraded_spatial_lowlight.max().item():.2f}]")
    
    # 可视化
    print("\n📊 生成可视化对比图...")
    fig, axes = plt.subplots(2, 4, figsize=(16, 8))
    
    axes[0,0].imshow(clean[0,0].cpu(), cmap='gray')
    axes[0,0].set_title('Clean')
    axes[0,0].axis('off')
    
    axes[0,1].imshow(degraded_global[0,0].cpu(), cmap='gray')
    axes[0,1].set_title('Global Mixed')
    axes[0,1].axis('off')
    
    axes[0,2].imshow(degraded_spatial_mixed[0,0].cpu(), cmap='gray')
    axes[0,2].set_title('Spatial Mixed')
    axes[0,2].axis('off')
    
    axes[0,3].imshow(intensity_map[0,0].cpu(), cmap='jet')
    axes[0,3].set_title('Noise Intensity')
    axes[0,3].axis('off')
    
    axes[1,0].imshow(degraded_spatial_noise[0,0].cpu(), cmap='gray')
    axes[1,0].set_title('Spatial Noise')
    axes[1,0].axis('off')
    
    axes[1,1].imshow(degraded_spatial_blur[0,0].cpu(), cmap='gray')
    axes[1,1].set_title('Spatial Blur')
    axes[1,1].axis('off')
    
    axes[1,2].imshow(degraded_spatial_lowlight[0,0].cpu(), cmap='gray')
    axes[1,2].set_title('Spatial Low-light')
    axes[1,2].axis('off')
    
    axes[1,3].imshow(intensity_map[0,2].cpu(), cmap='jet')
    axes[1,3].set_title('Low-light Intensity')
    axes[1,3].axis('off')
    
    plt.tight_layout()
    plt.savefig('degradation_test.png', dpi=150)
    plt.show()
    
    print("\n✅ 可视化结果已保存至: degradation_test.png")