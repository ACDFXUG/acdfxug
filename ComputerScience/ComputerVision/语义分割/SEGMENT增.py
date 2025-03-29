import torch
import torch.nn as nn
import torch.optim as optim
import torchvision.transforms as transforms
from torch.utils.data import DataLoader
import matplotlib.pyplot as plt
from PIL import Image
import numpy as np
import os
from sklearn.metrics import (
    jaccard_score,  # 用于计算IoU
    f1_score,       # 用于计算F1
    accuracy_score, # 用于计算Accuracy
    recall_score,   # 用于计算Recall
)
from scipy.spatial.distance import directed_hausdorff  # 用于计算HD
from medpy.metric.binary import dc  # 用于计算Dice系数
import xml.etree.ElementTree as ET
from tqdm import tqdm

class VOCDataset(torch.utils.data.Dataset):
    def __init__(self, root, image_set='train', transform=None):
        """
        初始化VOC数据集
        
        参数:
            root: 数据集根目录
            image_set: 'train'或'val'
            transform: 数据增强变换
        """
        self.root = root
        self.transform = transform
        self.image_set = image_set
        
        # 设置路径
        self.img_dir = os.path.join(root, "JPEGImages")
        self.label_dir = os.path.join(root, "SegmentationClass")
        self.image_set_dir = os.path.join(root, "ImageSets", "Segmentation")
        
        # 读取划分好的训练集或验证集文件列表
        set_file = os.path.join(self.image_set_dir, f"{image_set}.txt")
        with open(set_file, 'r') as f:
            self.file_names = [x.strip() for x in f.readlines()]
        
        # 检查文件是否存在
        self.valid_files = []
        for name in self.file_names:
            img_path = os.path.join(self.img_dir, f"{name}.jpg")
            label_path = os.path.join(self.label_dir, f"{name}.png")
            if os.path.exists(img_path) and os.path.exists(label_path):
                self.valid_files.append(name)
        
        if transform is None:
            # 默认的数据预处理
            self.transform = transforms.Compose([
                transforms.Resize((256, 256)),
                transforms.ToTensor(),
                transforms.Normalize(mean=[0.485, 0.456, 0.406],
                                   std=[0.229, 0.224, 0.225])
            ])
            
        # 标签转换
        self.label_transform = transforms.Compose([
            transforms.Resize((256, 256), interpolation=Image.NEAREST),
            transforms.ToTensor()
        ])

    def __len__(self):
        return len(self.valid_files)

    def __getitem__(self, idx):
        file_name = self.valid_files[idx]
        
        # 加载图像
        img_path = os.path.join(self.img_dir, f"{file_name}.jpg")
        img = Image.open(img_path).convert('RGB')
        
        # 加载标签
        label_path = os.path.join(self.label_dir, f"{file_name}.png")
        label = Image.open(label_path)
        
        # 应用变换
        if self.transform:
            img = self.transform(img)
            label = self.label_transform(label)
            label = (label * 255).long()  # 将标签转换为整数
        
        return img, label.squeeze(0)  # 移除标签的通道维度

def get_dataloaders(batch_size=8):
    """
    获取训练和验证数据加载器
    
    参数:
        batch_size: 批量大小
        
    返回:
        train_loader: 训练数据加载器
        val_loader: 验证数据加载器
    """
    # 数据预处理
    transform = transforms.Compose([
        transforms.Resize((256, 256)),
        transforms.ToTensor(),
        transforms.Normalize(mean=[0.485, 0.456, 0.406],
                            std=[0.229, 0.224, 0.225])
    ])
    
    # 创建数据集
    train_dataset = VOCDataset(root="./data", image_set='train', transform=transform)
    val_dataset = VOCDataset(root="./data", image_set='val', transform=transform)
    
    # 创建数据加载器
    train_loader = DataLoader(train_dataset, batch_size=batch_size, 
                             shuffle=True, num_workers=4, pin_memory=True)
    val_loader = DataLoader(val_dataset, batch_size=batch_size, 
                           shuffle=False, num_workers=4, pin_memory=True)
    
    return train_loader, val_loader

class SimpleSegmentationModel(nn.Module):
    def __init__(self, num_classes=21):
        super(SimpleSegmentationModel, self).__init__()
        
        # 编码器部分
        self.enc1 = self._encoder_block(3, 64)
        self.enc2 = self._encoder_block(64, 128)
        self.enc3 = self._encoder_block(128, 256)
        self.enc4 = self._encoder_block(256, 512)
        self.enc5 = self._encoder_block(512, 1024)
        
        # 解码器部分
        self.dec1 = self._decoder_block(1024, 512)
        self.dec2 = self._decoder_block(1024, 256)  # 512+512=1024
        self.dec3 = self._decoder_block(512, 128)   # 256+256=512
        self.dec4 = self._decoder_block(256, 64)    # 128+128=256
        
        self.final_conv = nn.Conv2d(64, num_classes, kernel_size=1)
        self.pool = nn.MaxPool2d(kernel_size=2, stride=2)
        
    def _encoder_block(self, in_channels, out_channels):
        return nn.Sequential(
            nn.Conv2d(in_channels, out_channels, kernel_size=3, padding=1),
            nn.BatchNorm2d(out_channels),
            nn.ReLU(inplace=True),
            nn.Conv2d(out_channels, out_channels, kernel_size=3, padding=1),
            nn.BatchNorm2d(out_channels),
            nn.ReLU(inplace=True)
        )
    
    def _decoder_block(self, in_channels, out_channels):
        return nn.Sequential(
            nn.Conv2d(in_channels, out_channels, kernel_size=3, padding=1),
            nn.BatchNorm2d(out_channels),
            nn.ReLU(inplace=True),
            nn.Conv2d(out_channels, out_channels, kernel_size=3, padding=1),
            nn.BatchNorm2d(out_channels),
            nn.ReLU(inplace=True),
            nn.Upsample(scale_factor=2, mode='bilinear', align_corners=True)
        )
    
    def forward(self, x):
        # 编码路径
        enc1 = self.enc1(x)
        enc2 = self.enc2(self.pool(enc1))
        enc3 = self.enc3(self.pool(enc2))
        enc4 = self.enc4(self.pool(enc3))
        enc5 = self.enc5(self.pool(enc4))
        
        # 解码路径 + 跳跃连接
        dec1 = self.dec1(enc5)
        dec1 = torch.cat([dec1, enc4], dim=1)
        
        dec2 = self.dec2(dec1)
        dec2 = torch.cat([dec2, enc3], dim=1)
        
        dec3 = self.dec3(dec2)
        dec3 = torch.cat([dec3, enc2], dim=1)
        
        dec4 = self.dec4(dec3)  # 不拼接enc1
        
        out = self.final_conv(dec4)
        return out
def train_model(model, train_loader, criterion, optimizer, num_epochs=20, device='cuda'):
    """
    训练模型（全部epoch完成后测试）
    
    参数:
        model: 要训练的模型
        train_loader: 训练数据加载器
        criterion: 损失函数
        optimizer: 优化器
        num_epochs: 训练轮数
        device: 训练设备
        
    返回:
        model: 训练好的模型
        train_losses: 训练损失列表
    """
    model.to(device)
    train_losses = []
    
    for epoch in range(num_epochs):
        model.train()
        running_loss = 0.0
        progress_bar = tqdm(train_loader, desc=f'Epoch {epoch+1}/{num_epochs}', leave=False)
        
        for images, masks in progress_bar:
            images = images.to(device)
            masks = masks.to(device)
            
            # 前向传播
            outputs = model(images)
            loss = criterion(outputs, masks)
            
            # 反向传播和优化
            optimizer.zero_grad()
            loss.backward()
            optimizer.step()
            
            running_loss += loss.item()
            progress_bar.set_postfix({'loss': loss.item()})
        
        # 记录训练损失
        epoch_loss = running_loss / len(train_loader)
        train_losses.append(epoch_loss)
        print(f'Epoch {epoch+1}/{num_epochs}, Loss: {epoch_loss:.4f}')
    
    # 保存最终模型
    torch.save(model.state_dict(), 'final_model.pth')
    return model, train_losses

def visualize_results(model, dataloader, num_images=5, device='cuda'):
    """
    可视化分割结果
    
    参数:
        model: 训练好的模型
        dataloader: 数据加载器
        num_images: 要可视化的图像数量
        device: 设备
    """
    model.eval()
    model.to(device)
    
    # 获取一批数据
    images, true_masks = next(iter(dataloader))
    images = images[:num_images].to(device)
    true_masks = true_masks[:num_images].cpu().numpy()
    
    with torch.no_grad():
        pred_masks = model(images).argmax(dim=1).cpu().numpy()
    
    # 反归一化图像
    mean = torch.tensor([0.485, 0.456, 0.406]).view(3, 1, 1).to(device)
    std = torch.tensor([0.229, 0.224, 0.225]).view(3, 1, 1).to(device)
    denorm_images = images * std + mean
    denorm_images = denorm_images.cpu().permute(0, 2, 3, 1).numpy()
    denorm_images = np.clip(denorm_images, 0, 1)
    
    # 可视化
    plt.figure(figsize=(15, 5*num_images))
    for i in range(num_images):
        # 显示原始图像
        plt.subplot(num_images, 3, i*3 + 1)
        plt.imshow(denorm_images[i])
        plt.title('Original Image')
        plt.axis('off')
        
        # 显示预测分割
        plt.subplot(num_images, 3, i*3 + 2)
        plt.imshow(pred_masks[i], cmap='jet', vmin=0, vmax=20)
        plt.title('Predicted Mask')
        plt.axis('off')
        
        # 显示真实分割
        plt.subplot(num_images, 3, i*3 + 3)
        plt.imshow(true_masks[i], cmap='jet', vmin=0, vmax=20)
        plt.title('Ground Truth')
        plt.axis('off')
    
    plt.tight_layout()
    plt.savefig('segmentation_results.png')
    plt.show()

def calculate_metrics(pred, target, num_classes=21):
    """
    计算多个分割评估指标 mIoU, Dice, HD, Accuracy, Recall, F1
    
    参数:
        pred: 预测的分割图 (H, W)
        target: 真实的分割图 (H, W)
        num_classes: 类别数量
        
    返回:
        metrics: 包含各项指标的字典
    """
    metrics = {}
    
    # 将输入展平为一维数组
    pred_flat = pred.flatten()
    target_flat = target.flatten()
    
    # 计算mIoU
    metrics['mIoU'] = jaccard_score(target_flat, pred_flat, 
                                  average='macro', 
                                  labels=range(num_classes),
                                  zero_division=0)
    
    # 计算Dice系数 (等价于F1 score)
    metrics['Dice'] = f1_score(target_flat, pred_flat, 
                              average='macro', 
                              labels=range(num_classes),
                              zero_division=0)
    
    # 计算Hausdorff距离
    hd_scores = []
    for cls in range(num_classes):
        pred_mask = (pred == cls)
        target_mask = (target == cls)
        
        if not np.any(pred_mask) or not np.any(target_mask):
            hd_scores.append(0)
            continue
            
        pred_points = np.array(np.where(pred_mask)).T
        target_points = np.array(np.where(target_mask)).T
        
        hd = max(directed_hausdorff(pred_points, target_points)[0],
                directed_hausdorff(target_points, pred_points)[0])
        hd_scores.append(hd)
    
    metrics['HD'] = np.mean(hd_scores) if hd_scores else 0
    
    # 计算Accuracy
    metrics['Accuracy'] = accuracy_score(target_flat, pred_flat)
    
    # 计算Recall
    metrics['Recall'] = recall_score(target_flat, pred_flat, 
                                   average='macro', 
                                   labels=range(num_classes),
                                   zero_division=0)
    
    # 计算F1 score (与Dice相同)
    metrics['F1'] = metrics['Dice']
    
    return metrics

def test_model(model, dataloader, device='cuda'):
    """
    测试模型性能
    
    参数:
        model: 要测试的模型
        dataloader: 测试数据加载器
        device: 设备
        
    返回:
        avg_metrics: 平均指标字典
    """
    model.eval()
    model.to(device)
    
    # 初始化所有指标的累加器
    total_metrics = {
        'mIoU': 0,
        'Dice': 0,
        'HD': 0,
        'Accuracy': 0,
        'Recall': 0,
        'F1': 0
    }
    num_batches = 0
    
    with torch.no_grad():
        for images, masks in tqdm(dataloader, desc='Testing'):
            images = images.to(device)
            masks = masks.cpu().numpy()
            
            outputs = model(images)
            preds = outputs.argmax(dim=1).cpu().numpy()
            
            # 逐样本计算指标
            batch_metrics = {
                'mIoU': 0,
                'Dice': 0,
                'HD': 0,
                'Accuracy': 0,
                'Recall': 0,
                'F1': 0
            }
            num_samples = 0
            
            for pred, mask in zip(preds, masks):
                sample_metrics = calculate_metrics(pred, mask)
                
                for metric_name in batch_metrics.keys():
                    batch_metrics[metric_name] += sample_metrics[metric_name]
                
                num_samples += 1
            
            # 计算批次平均指标
            for metric_name in batch_metrics.keys():
                batch_metrics[metric_name] /= num_samples
                total_metrics[metric_name] += batch_metrics[metric_name]
            
            num_batches += 1
    
    # 计算所有批次的平均指标
    avg_metrics = {k: v / num_batches for k, v in total_metrics.items()}
    
    # 打印所有指标
    print("\n评估指标:")
    print(f"mIoU: {avg_metrics['mIoU']:.4f}")
    print(f"Dice: {avg_metrics['Dice']:.4f}")
    print(f"Hausdorff Distance: {avg_metrics['HD']:.4f}")
    print(f"Accuracy: {avg_metrics['Accuracy']:.4f}")
    print(f"Recall: {avg_metrics['Recall']:.4f}")
    print(f"F1 Score: {avg_metrics['F1']:.4f}")
    
    return avg_metrics

if __name__ == "__main__":
    # 设置随机种子以保证可重复性
    torch.manual_seed(42)
    np.random.seed(42)
    
    # 检查CUDA是否可用
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    print(f"Using device: {device}")
    
    # 获取数据加载器
    batch_size = 8
    train_loader, val_loader = get_dataloaders(batch_size=batch_size)
    
    # 初始化模型
    num_classes = 21  # PASCAL VOC有21类 (包括背景)0
    model = SimpleSegmentationModel(num_classes=num_classes)
    
    # 定义损失函数和优化器
    criterion = nn.CrossEntropyLoss(ignore_index=255)
    optimizer = optim.Adam(model.parameters(), lr=1e-4, weight_decay=1e-4)
    
    # 训练模型（不传val_loader）
    num_epochs = 100
    print("Starting training without validation...")
    model, train_losses = train_model(
        model, train_loader, criterion, optimizer, 
        num_epochs=num_epochs, device=device
    )
    
    # 可视化训练损失
    plt.figure(figsize=(6, 4))
    plt.plot(train_losses)
    plt.xlabel('Epoch')
    plt.ylabel('Loss')
    plt.title('Training Loss Curve')
    plt.savefig('training_loss.png')
    plt.show()
    
    # 训练完成后测试
    print("\nTesting after all epochs...")
    test_model(model, val_loader, device=device)
    
    # 可视化结果
    print("\nVisualizing results...")
    visualize_results(model, val_loader, num_images=3, device=device)