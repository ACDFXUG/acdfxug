import os
from PIL import Image
from torch.utils.data import Dataset, DataLoader
import torchvision.transforms as transforms
import torch

class MedicalRestorationDataset(Dataset):
    def __init__(self, root_dir, transform=None):
        """
        root_dir: 指向 train, valid 或 test 文件夹的路径
                  例如: './Graduate/MRI-Images-of-Brain-Tumor/timri/train'
        """
        self.root_dir = root_dir
        self.transform = transform
        self.image_paths = []
        
        categories = ['glioma', 'meningioma', 'no-tumor', 'pituitary']
        
        print(f"正在扫描数据集：{root_dir} ...")
        count = 0
        for cat in categories:
            cat_path = os.path.join(root_dir, cat)
            if not os.path.exists(cat_path):
                continue
            for img_name in os.listdir(cat_path):
                if img_name.lower().endswith(('.jpg', '.jpeg', '.png')):
                    self.image_paths.append(os.path.join(cat_path, img_name))
                    count += 1
        print(f"✅ 找到 {count} 张图像。")

    def __len__(self):
        return len(self.image_paths)

    def __getitem__(self, idx):
        img_path = self.image_paths[idx]
        
        # 1. 读取图像
        image = Image.open(img_path)
        
        # 2. 转为灰度图 (L 模式)，因为医学图像通常是单通道的
        # 如果原图是 RGB，这会将 R,G,B 平均为灰度；如果是单通道 JPG，保持不变
        image = image.convert('L') 
        
        # 3. 应用变换
        if self.transform:
            image = self.transform(image)
            
        # 返回：(退化输入，干净标签) -> 初始阶段两者都是原图
        # 退化操作将在 training loop 中动态进行，以便每次 epoch 看到不同的噪声
        return image, image 

# 定义变换：只需 ToTensor 和 Normalize，不需要 Resize！
# 输入将是 [1, 224, 224]
transform = transforms.Compose([
    transforms.ToTensor(),          # 转 tensor, 范围 [0, 1]
    transforms.Normalize(mean=[0.5], std=[0.5]) # 归一化到 [-1, 1]
])

if __name__ == "__main__":
    # 测试代码
    train_dir = '../MRI-Images-of-Brain-Tumor/timri/train'
    dataset = MedicalRestorationDataset(train_dir, transform=transform)
    loader = DataLoader(dataset, batch_size=4, shuffle=True)
    
    for imgs, labels in loader:
        print(f"Batch shape: {imgs.shape}") # 应输出 torch.Size([4, 1, 224, 224])
        print(f"Min value: {imgs.min()}, Max value: {imgs.max()}")
        break