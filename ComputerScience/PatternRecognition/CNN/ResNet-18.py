import torch
import torch.nn as nn
import torch.optim as optim
import torchvision.transforms as transforms
from torch.utils.data import DataLoader, Dataset
import torchvision.models as models # 将模型导入移到这里
import pickle
import os
import numpy as np
from tqdm import tqdm # 导入 tqdm

def unpickle(file):
    """加载 pickle 文件"""
    with open(file, 'rb') as f:
        dict = pickle.load(f, encoding='bytes')
    return dict

def load_cifar10_data(data_dir):
    """从指定目录加载 CIFAR-10 数据"""
    train_data = []
    train_labels = []
    
    # 加载训练批次
    for i in range(1, 6): # data_batch_1 到 data_batch_5
        file_path = os.path.join(data_dir, f'data_batch_{i}')
        batch_dict = unpickle(file_path)
        train_data.append(batch_dict[b'data'])
        train_labels += batch_dict[b'labels']
    
    # 合并训练数据
    train_data = np.vstack(train_data).reshape(-1, 3, 32, 32) # (N, 3072) -> (N, 3, 32, 32)
    train_data = train_data.transpose((0, 2, 3, 1)) # (N, 3, 32, 32) -> (N, 32, 32, 3)
    
    # 加载测试批次
    test_file_path = os.path.join(data_dir, 'test_batch')
    test_dict = unpickle(test_file_path)
    test_data = test_dict[b'data'].reshape(-1, 3, 32, 32).transpose((0, 2, 3, 1)) # (N, 3, 32, 32) -> (N, 32, 32, 3)
    test_labels = test_dict[b'labels']
    
    # 加载标签名称 (可选，用于打印)
    meta_file_path = os.path.join(data_dir, 'batches.meta')
    meta_dict = unpickle(meta_file_path)
    label_names = [name.decode('utf-8') for name in meta_dict[b'label_names']]

    return (train_data, np.array(train_labels)), (test_data, np.array(test_labels)), label_names

# --- 1. 检查设备 ---
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
print(f"Using device: {device}")

# --- 2. 加载本地 CIFAR-10 数据 ---
print("Loading CIFAR-10 data from './cifar10'...")
(train_images, train_labels), (test_images, test_labels), classes = load_cifar10_data('./cifar10')
print(f"Training data shape: {train_images.shape}, labels shape: {train_labels.shape}")
print(f"Test data shape: {test_images.shape}, labels shape: {test_labels.shape}")

# --- 3. 定义数据预处理 Transform ---
# 注意：ToTensor 会将 HWC (Height, Width, Channels) 格式的 numpy array 转换为 CHW tensor
# CIFAR-10 图像尺寸为 32x32，使用常见的数据增强技术
transform_train = transforms.Compose([
    transforms.ToPILImage(), # 先转换为 PIL Image 才能使用 RandomCrop 和 RandomHorizontalFlip
    transforms.RandomCrop(32, padding=4),
    transforms.RandomHorizontalFlip(),
    transforms.ToTensor(), # 转换为 Tensor，并归一化到 [0, 1]
    transforms.Normalize((0.4914, 0.4822, 0.4465), (0.2023, 0.1994, 0.2010)), # CIFAR-10 均值和标准差
])

transform_test = transforms.Compose([
    transforms.ToPILImage(),
    transforms.ToTensor(),
    transforms.Normalize((0.4914, 0.4822, 0.4465), (0.2023, 0.1994, 0.2010)),
])

# --- 4. 创建 Dataset 和 DataLoader ---
class CIFAR10Dataset(torch.utils.data.Dataset):
    def __init__(self, images, labels, transform=None):
        self.images = images
        self.labels = labels
        self.transform = transform

    def __len__(self):
        return len(self.images)

    def __getitem__(self, idx):
        image = self.images[idx]
        label = self.labels[idx]
        if self.transform:
            image = self.transform(image)
        return image, label

# --- 主代码块 ---
if __name__ == '__main__':
    best_acc = 0
    num_epochs = 50 # 可以根据需要调整
    # 将所有可能触发 multiprocessing 的代码放入此块
    train_dataset = CIFAR10Dataset(train_images, train_labels, transform=transform_train)
    test_dataset = CIFAR10Dataset(test_images, test_labels, transform=transform_test)

    trainloader = DataLoader(train_dataset, batch_size=128, shuffle=True, num_workers=2)
    testloader = DataLoader(test_dataset, batch_size=100, shuffle=False, num_workers=2)

    print(f"DataLoaders created. Train batches: {len(trainloader)}, Test batches: {len(testloader)}")

    # --- 5. 加载预定义的 ResNet-18 模型 ---
    # CIFAR-10 只有 10 个类别，而 ResNet-18 默认是为 ImageNet 的 1000 类设计的
    # 因此需要修改最后的全连接层 (fc layer)
    model = models.resnet18(weights=None) # 使用 models.resnet18
    # 修改最后一层以适应 CIFAR-10 的 10 个类别
    model.fc = nn.Linear(model.fc.in_features, 10)
    model = model.to(device)

    print("Model loaded and modified for CIFAR-10.")

    # --- 6. 定义损失函数和优化器 ---
    criterion = nn.CrossEntropyLoss()
    # optimizer = optim.Adam(model.parameters(), lr=0.001,  weight_decay=5e-4) #momentum=0.9,
    optimizer = optim.SGD(model.parameters(), lr=0.1, momentum=0.9, weight_decay=5e-4)
    # 学习率调度器，通常在训练过程中降低学习率
    # scheduler = optim.lr_scheduler.StepLR(optimizer, step_size=7, gamma=0.1)
    scheduler = optim.lr_scheduler.ExponentialLR(optimizer, gamma=0.95) # 每个 epoch 后 lr *= 0.95

    # --- 7. 训练函数 (带 tqdm) ---
    def train(epoch):
        model.train() # 设置模型为训练模式
        running_loss = 0.0
        correct = 0
        total = 0

        # 使用 tqdm 包装 DataLoader，并显式传入总批次数
        pbar = tqdm(trainloader, desc=f'Epoch {epoch}/{num_epochs} - Training', leave=False, total=len(trainloader))
        for batch_idx, (inputs, labels) in enumerate(pbar):
            inputs, labels = inputs.to(device), labels.to(device)

            optimizer.zero_grad()

            outputs = model(inputs)
            loss = criterion(outputs, labels)
            loss.backward()
            optimizer.step()

            running_loss += loss.item()
            _, predicted = outputs.max(1)
            total += labels.size(0)
            correct += predicted.eq(labels).sum().item()

            # 更新进度条的描述信息，显示实时损失和准确率
            pbar.set_postfix({
                'Loss': f'{running_loss / (batch_idx + 1):.3f}', # 使用 batch_idx + 1
                'Acc': f'{100.*correct/total:.2f}%'
            })

        scheduler.step() # 每个 epoch 后更新学习率
        # 返回整个 epoch 的平均损失和最终准确率
        return running_loss / len(trainloader), 100.*correct/total

    # --- 8. 测试函数 (带 tqdm) ---
    def test():
        model.eval() # 设置模型为评估模式
        test_loss = 0.0
        correct = 0
        total = 0
        # 使用 tqdm 包装测试 DataLoader，并显式传入总批次数
        pbar = tqdm(testloader, desc='Testing', leave=False, total=len(testloader))
        with torch.no_grad(): # 禁用梯度计算以节省内存和加速
            for batch_idx, (inputs, labels) in enumerate(pbar):
                inputs, labels = inputs.to(device), labels.to(device)
                outputs = model(inputs)
                loss = criterion(outputs, labels)

                test_loss += loss.item()
                _, predicted = outputs.max(1)
                total += labels.size(0)
                correct += predicted.eq(labels).sum().item()

                # 更新测试进度条的描述信息
                pbar.set_postfix({
                    'Test Loss': f'{test_loss / (batch_idx + 1):.3f}',
                    'Test Acc': f'{100.*correct/total:.2f}%'
                })

        # 返回整个测试集的平均损失和准确率
        return test_loss / len(testloader), 100.*correct/total

    # --- 9. 主训练循环 ---
    
    print("Starting Training...")

    for epoch in range(1, num_epochs + 1):
        print(f'\nEpoch {epoch}/{num_epochs}')
        train_loss, train_acc = train(epoch)
        test_loss, test_acc = test()
        
        print(f'Epoch {epoch} finished.')
        print(f'  Train Loss: {train_loss:.3f}, Train Acc: {train_acc:.2f}%')
        print(f'  Test Loss: {test_loss:.3f}, Test Acc: {test_acc:.2f}%')
        
        if test_acc > best_acc:
            print(f'New best accuracy: {test_acc:.2f}%. Saving model...')
            torch.save(model.state_dict(), 'best_resnet18_cifar10.pth')
            best_acc = test_acc

    print(f'\nFinished Training. Best Test Accuracy: {best_acc:.2f}%')

    # --- 10. 加载最佳模型并进行最终测试 ---
    model.load_state_dict(torch.load('best_resnet18_cifar10.pth'))
    model.eval()
    final_test_loss, final_test_acc = test()
    print(f'Final Test Loss on Best Model: {final_test_loss:.3f}')
    print(f'Final Test Accuracy on Best Model: {final_test_acc:.2f}%')
