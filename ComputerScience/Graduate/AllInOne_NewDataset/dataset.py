import os
import h5py
import numpy as np
from torch.utils.data import Dataset, DataLoader
import torch
from skimage.transform import iradon
import scipy.ndimage
import hashlib

class MedicalRestorationDataset(Dataset):
    def __init__(self, hdf5_file_paths:list[str], input_key='data', target_key='data', is_observation_data=True, cache_dir='./hdf5Cache'):
        """
        hdf5_file_paths: hdf5文件路径列表
        input_key: hdf5文件中观测值的键名 (data)
        target_key: hdf5文件中真实值的键名 (data)
        is_observation_data: 是否是observation数据（正弦图），如果是，则需要重建
        cache_dir: 缓存目录
        """
        self.hdf5_file_paths = hdf5_file_paths
        self.input_key = input_key
        self.target_key = target_key
        self.is_observation_data = is_observation_data
        self.cache_dir = cache_dir
        
        # 创建缓存目录
        os.makedirs(cache_dir, exist_ok=True)
        
        print(f"正在加载 {len(hdf5_file_paths)} 个hdf5文件...")
        self.data = []
        
        for hdf5_path in hdf5_file_paths:
            # 生成缓存文件路径
            cache_file_path = self._get_cache_path(hdf5_path)
            
            if self.is_observation_data:
                # 尝试从缓存加载
                if os.path.exists(cache_file_path):
                    print(f"从缓存加载: {cache_file_path}")
                    cached_data = np.load(cache_file_path, allow_pickle=True)
                    for item in cached_data:
                        self.data.append({
                            'input': item['input'],
                            'target': item['target']
                        })
                else:
                    # 重建并缓存
                    print(f"正在处理并缓存: {hdf5_path}")
                    with h5py.File(hdf5_path, 'r') as f:
                        sinograms = f[self.input_key][:]  # shape: (128, 1000, 513)
                        
                        # 获取对应的ground truth文件路径
                        gt_path = hdf5_path.replace('\\observation_', '\\ground_truth_')
                        if not os.path.exists(gt_path):
                            # 如果没找到对应的ground truth文件，则尝试在相同路径下查找
                            base_name = os.path.basename(hdf5_path)
                            dir_name = os.path.dirname(hdf5_path)
                            gt_name = base_name.replace('observation', 'ground_truth')
                            gt_path = os.path.join(dir_name, gt_name).replace('\\', '/')
                            
                        if os.path.exists(gt_path):
                            with h5py.File(gt_path, 'r') as gt_f:
                                ground_truths = gt_f[self.target_key][:]  # shape: (128, 362, 362)
                        else:
                            print(f"⚠️ 未找到对应的ground truth文件: {gt_path}")
                            continue
                        
                        # 对每个切片进行重建
                        cache_items = []
                        for i in range(sinograms.shape[0]):
                            # 获取正弦图
                            sinogram = sinograms[i]  # shape: (1000, 513)
                            
                            # 进行CT重建（使用iradon变换）
                            reconstructed = self.reconstruct_ct_image(sinogram)  # shape: (362, 362)
                            
                            # 获取对应的ground truth
                            gt_slice = ground_truths[i]  # shape: (362, 362)
                            
                            # 添加到数据集
                            self.data.append({
                                'input': reconstructed,
                                'target': gt_slice
                            })
                            
                            # 添加到缓存项
                            cache_items.append({
                                'input': reconstructed,
                                'target': gt_slice
                            })
                        
                        # 保存到缓存
                        np.save(cache_file_path, cache_items)
                        print(f"已缓存到: {cache_file_path}")
            else:
                # Ground truth数据，形状为(128, 362, 362)
                with h5py.File(hdf5_path, 'r') as f:
                    targets = f[self.target_key][:]  # shape: (128, 362, 362)
                    
                    # 遍历每个切片
                    for i in range(targets.shape[0]):
                        self.data.append({
                            'input': targets[i],  # 使用目标作为输入（用于验证集）
                            'target': targets[i]
                        })
        print(f"✅ 加载完成，共有 {len(self.data)} 张图像对。")

    def _get_cache_path(self, hdf5_path):
        """根据hdf5文件路径生成缓存文件路径"""
        # 使用文件路径的哈希值作为缓存文件名的一部分，确保唯一性
        path_hash = hashlib.md5(hdf5_path.encode()).hexdigest()[:8]
        base_name = os.path.splitext(os.path.basename(hdf5_path))[0]
        cache_filename = f"{base_name}_{path_hash}_cached.npy"
        return os.path.join(self.cache_dir, cache_filename)

    def reconstruct_ct_image(self, sinogram):
        """
        使用iradon变换重建CT图像
        sinogram: shape (num_angles, num_detectors), e.g., (1000, 513)
        """
        # 确定角度
        num_angles = sinogram.shape[0]
        theta = np.linspace(0., 180., num_angles, endpoint=False)
        
        # 执行iradon变换
        # 注意：输入的正弦图尺寸可能与期望的输出尺寸不同
        # 我们需要将重建的图像resize到362x362
        reconstruction = iradon(sinogram.T, theta=theta, circle=False)
        
        # 调整大小到期望的尺寸 (362, 362)
        zoom_factor = (362 / reconstruction.shape[0], 362 / reconstruction.shape[1])
        reconstruction_resized = scipy.ndimage.zoom(reconstruction, zoom_factor, order=1)
        
        return reconstruction_resized

    def __len__(self):
        return len(self.data)

    def __getitem__(self, idx):
        sample = self.data[idx]
        
        # 获取输入和目标图像
        input_img = torch.from_numpy(sample['input']).float().unsqueeze(0)  # 添加通道维度
        target_img = torch.from_numpy(sample['target']).float().unsqueeze(0)  # 添加通道维度
        
        # 归一化到 [-1, 1] 范围
        input_img = 2 * (input_img - input_img.min()) / (input_img.max() - input_img.min()) - 1
        target_img = 2 * (target_img - target_img.min()) / (target_img.max() - target_img.min()) - 1
        
        # 防止数值溢出
        input_img = torch.clamp(input_img, -1, 1)
        target_img = torch.clamp(target_img, -1, 1)
        
        return input_img, target_img

def get_data_loaders(base_path, train_size=40, val_size=10, test_size=10, batch_size=4, cache_dir='./hdf5Cache'):
    """
    获取训练、验证和测试数据加载器
    """
    # 构建训练集文件路径（使用observation_train作为输入，自动匹配ground_truth_train作为目标）
    train_observation_paths = [
        os.path.join(base_path, 'observation_train', f'observation_train_{i:03d}.hdf5')
        for i in range(train_size)
    ]
    
    # 构建验证集文件路径（如果存在validation文件夹则使用，否则使用test文件夹的一部分）
    if os.path.exists(os.path.join(base_path, 'observation_validation')):
        val_observation_paths = [
            os.path.join(base_path, 'observation_validation', f'observation_validation_{i:03d}.hdf5')
            for i in range(val_size)
        ]
    else:
        # 如果没有专门的validation文件夹，则从test文件夹中取一部分作为验证集
        val_observation_paths = [
            os.path.join(base_path, 'observation_test', f'observation_test_{i:03d}.hdf5')
            for i in range(val_size)
        ]
    
    # 构建测试集文件路径
    test_observation_paths = [
        os.path.join(base_path, 'observation_test', f'observation_test_{i+val_size:03d}.hdf5')
        for i in range(test_size)
    ]
    
    # 检查文件是否存在
    all_paths = train_observation_paths + val_observation_paths + test_observation_paths
    missing_paths = [path for path in all_paths if not os.path.exists(path)]
    
    if missing_paths:
        print(f"⚠️ 以下文件不存在: {missing_paths[:5]}...")  # 只显示前5个缺失文件
        # 尝试获取实际存在的文件
        actual_train_obs = []
        actual_val_obs = []
        actual_test_obs = []
        
        for i in range(300):  # 尝试最多300个训练文件
            obs_path = os.path.join(base_path, 'observation_train', f'observation_train_{i:03d}.hdf5')
            if os.path.exists(obs_path):
                actual_train_obs.append(obs_path)
            else:
                break
                
        # 查找验证数据
        if os.path.exists(os.path.join(base_path, 'observation_validation')):
            for i in range(100):  # 尝试最多100个验证文件
                obs_path = os.path.join(base_path, 'observation_validation', f'observation_validation_{i:03d}.hdf5')
                if os.path.exists(obs_path):
                    actual_val_obs.append(obs_path)
                else:
                    break
        else:
            # 从test中取前10个作为验证集
            for i in range(min(10, 100)):  
                obs_path = os.path.join(base_path, 'observation_test', f'observation_test_{i:03d}.hdf5')
                if os.path.exists(obs_path):
                    actual_val_obs.append(obs_path)
                else:
                    break
        
        # 查找测试数据
        for i in range(len(actual_val_obs), min(len(actual_val_obs) + 10, 200)):
            obs_path = os.path.join(base_path, 'observation_test', f'observation_test_{i:03d}.hdf5')
            if os.path.exists(obs_path):
                actual_test_obs.append(obs_path)
            else:
                break
                
        print(f"✅ 找到 {len(actual_train_obs)} 个训练文件，{len(actual_val_obs)} 个验证文件，{len(actual_test_obs)} 个测试文件")
        
        # 使用实际找到的文件，指定is_observation_data=True表示需要重建
        train_dataset = MedicalRestorationDataset(actual_train_obs, 'data', 'data', is_observation_data=True, cache_dir=cache_dir)
        val_dataset = MedicalRestorationDataset(actual_val_obs, 'data', 'data', is_observation_data=True, cache_dir=cache_dir)
        test_dataset = MedicalRestorationDataset(actual_test_obs, 'data', 'data', is_observation_data=True, cache_dir=cache_dir)
    else:
        # 使用原始路径
        train_dataset = MedicalRestorationDataset(train_observation_paths, 'data', 'data', is_observation_data=True, cache_dir=cache_dir)
        val_dataset = MedicalRestorationDataset(val_observation_paths, 'data', 'data', is_observation_data=True, cache_dir=cache_dir)
        test_dataset = MedicalRestorationDataset(test_observation_paths, 'data', 'data', is_observation_data=True, cache_dir=cache_dir)

    train_loader = DataLoader(train_dataset, batch_size=batch_size, shuffle=True, num_workers=0)
    val_loader = DataLoader(val_dataset, batch_size=batch_size, shuffle=False, num_workers=0)
    test_loader = DataLoader(test_dataset, batch_size=batch_size, shuffle=False, num_workers=0)
    
    return train_loader, val_loader, test_loader

if __name__ == "__main__":
    # 测试代码
    base_path = '../LoDoPaB-CT'  # 相对于当前目录的路径
    train_loader, val_loader = get_data_loaders(base_path, train_size=2, val_size=1, batch_size=2)
    
    print("\n测试训练数据加载...")
    for inputs, targets in train_loader:
        print(f"Input batch shape: {inputs.shape}")  # 应输出类似 [2, 1, 362, 362]
        print(f"Target batch shape: {targets.shape}")  # 应输出类似 [2, 1, 362, 362]
        print(f"Input range: [{inputs.min():.3f}, {inputs.max():.3f}]")
        print(f"Target range: [{targets.min():.3f}, {targets.max():.3f}]")
        break
        
    print("\n测试验证数据加载...")
    for inputs, targets in val_loader:
        print(f"Input batch shape: {inputs.shape}")
        print(f"Target batch shape: {targets.shape}")
        print(f"Input range: [{inputs.min():.3f}, {inputs.max():.3f}]")
        print(f"Target range: [{targets.min():.3f}, {targets.max():.3f}]")
        break