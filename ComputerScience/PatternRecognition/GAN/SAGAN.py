import os
os.environ['KMP_DUPLICATE_LIB_OK']='True'

import io
import torch
import torch.nn as nn
import torch.optim as optim
import torch.nn.functional as F
from torch.utils.data import DataLoader, Dataset
import torchvision
import torchvision.transforms as transforms
import torchvision.models as models
import numpy as np
import matplotlib.pyplot as plt
import os
import random
from PIL import Image
import warnings
from scipy import linalg
import torch.nn.functional as F
warnings.filterwarnings('ignore')
import pyarrow.parquet as pq

# 设置随机种子
def set_seeds():
    torch.manual_seed(42)
    torch.cuda.manual_seed(42)
    np.random.seed(42)
    random.seed(42)
    torch.backends.cudnn.deterministic = True

device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
print(f"Using device: {device}")

# FID计算相关函数
def calculate_fid(mu1, sigma1, mu2, sigma2, eps=1e-6):
    """计算FID分数"""
    mu1 = np.atleast_1d(mu1)
    mu2 = np.atleast_1d(mu2)
    
    sigma1 = np.atleast_2d(sigma1)
    sigma2 = np.atleast_2d(sigma2)
    
    diff = mu1 - mu2
    
    # 计算sqrt((sigma1 * sigma2)^(1/2))
    covmean, _ = linalg.sqrtm(sigma1.dot(sigma2), disp=False)
    if not np.isfinite(covmean).all():
        msg = ('fid calculation produces singular product; '
               'adding %s to diagonal of cov estimates') % eps
        print(msg)
        offset = np.eye(sigma1.shape[0]) * eps
        covmean = linalg.sqrtm((sigma1 + offset).dot(sigma2 + offset))

    # 数值误差检查
    if np.iscomplexobj(covmean):
        if not np.allclose(np.diagonal(covmean).imag, 0, atol=1e-3):
            m = np.max(np.abs(covmean.imag))
            raise ValueError(f'Imaginary component {m}')
        covmean = covmean.real

    tr_covmean = np.trace(covmean)

    return (diff.dot(diff) + np.trace(sigma1) + 
            np.trace(sigma2) - 2 * tr_covmean)

class InceptionV3FeatureExtractor(nn.Module):
    """用于提取特征的InceptionV3模型"""
    def __init__(self):
        super(InceptionV3FeatureExtractor, self).__init__()
        inception = models.inception_v3(pretrained=True)
        inception.fc = nn.Identity()  # 移除分类层
        inception.aux_logits = False  # 禁用辅助输出
        self.inception = inception
        
    def forward(self, x):
        # 调整图像大小到299x299（Inception输入尺寸）
        if x.shape[2] != 299 or x.shape[3] != 299:
            x = F.interpolate(x, size=(299, 299), mode='bilinear', align_corners=False)
        
        # 确保输入在[0,1]范围内
        x = (x + 1) * 0.5  # 从[-1,1]转换到[0,1]
        x = torch.clamp(x, 0, 1)
        
        # 前向传播
        return self.inception(x)

def get_features(extractor, dataloader, num_samples=1000, device='cuda'):
    """提取特征"""
    extractor.eval()
    features = []
    
    with torch.no_grad():
        for i, batch in enumerate(dataloader):
            if isinstance(batch, tuple):
                images = batch[0]
            else:
                images = batch
                
            images = images.to(device)
            feat = extractor(images)
            features.append(feat.cpu().numpy())
            
            # 达到所需样本数时停止
            if len(features) * images.shape[0] >= num_samples:
                break
    
    features = np.concatenate(features, axis=0)
    return features[:num_samples]  # 确保精确的样本数

def calculate_fid_score(generator, real_dataloader, num_samples=1000, batch_size=32, device='cuda'):
    """计算FID分数"""
    print("Calculating FID score...")
    
    # 初始化特征提取器
    feature_extractor = InceptionV3FeatureExtractor().to(device)
    feature_extractor.eval()
    
    # 提取真实图像特征
    print("Extracting real image features...")
    real_features = get_features(feature_extractor, real_dataloader, num_samples, device)
    
    # 提取生成图像特征
    print("Extracting generated image features...")
    generator.eval()
    fake_features = []
    
    with torch.no_grad():
        num_batches = (num_samples + batch_size - 1) // batch_size
        for i in range(num_batches):
            z = torch.randn(batch_size, 128).to(device)
            fake_imgs = generator(z)
            fake_imgs = (fake_imgs + 1) * 0.5  # 转换到[0,1]
            fake_imgs = torch.clamp(fake_imgs, 0, 1)
            
            # 调整到Inception输入尺寸
            if fake_imgs.shape[2] != 299 or fake_imgs.shape[3] != 299:
                fake_imgs = F.interpolate(fake_imgs, size=(299, 299), mode='bilinear', align_corners=False)
            
            feat = feature_extractor.inception(fake_imgs)
            fake_features.append(feat.cpu().numpy())
            
            if (i + 1) * batch_size >= num_samples:
                break
    
    fake_features = np.concatenate(fake_features, axis=0)
    fake_features = fake_features[:num_samples]
    
    # 计算统计量
    mu_real = np.mean(real_features, axis=0)
    sigma_real = np.cov(real_features, rowvar=False)
    mu_fake = np.mean(fake_features, axis=0)
    sigma_fake = np.cov(fake_features, rowvar=False)
    
    # 计算FID
    fid_score = calculate_fid(mu_real, sigma_real, mu_fake, sigma_fake)
    
    print(f"FID Score: {fid_score:.2f}")
    return fid_score

# Dataset类
class CelebA256ParquetDataset(Dataset):
    def __init__(self, data_dir, transform=None, split='train', max_samples=None):
        self.data_dir = data_dir
        self.transform = transform
        self.split = split
        
        if split == 'train':
            self.parquet_files = [
                os.path.join(data_dir, 'train-00000-of-00006.parquet'),
                os.path.join(data_dir, 'train-00001-of-00006.parquet'),
                os.path.join(data_dir, 'train-00002-of-00006.parquet'),
                os.path.join(data_dir, 'train-00003-of-00006.parquet'),
                os.path.join(data_dir, 'train-00004-of-00006.parquet'),
                os.path.join(data_dir, 'train-00005-of-00006.parquet')
            ]
        else:  # validation
            self.parquet_files = [
                os.path.join(data_dir, 'validation-00000-of-00001.parquet')
            ]
        
        self.all_images = []
        total_loaded = 0
        
        print(f"Loading {split} dataset...")
        for parquet_file in self.parquet_files:
            if not os.path.exists(parquet_file):
                continue
                
            try:
                table = pq.read_table(parquet_file)
                images_dict_list = table['image'].to_numpy()
                
                for img_dict in images_dict_list:
                    try:
                        img_bytes = img_dict.get('bytes')
                        if img_bytes is None:
                            img_bytes = img_dict.get('data')
                        if img_bytes is None:
                            img_bytes = img_dict.get('value')
                        
                        if img_bytes is None:
                            continue
                        
                        img = Image.open(io.BytesIO(img_bytes))
                        if img.mode != 'RGB':
                            img = img.convert('RGB')
                        
                        self.all_images.append(img)
                        total_loaded += 1
                        
                        if max_samples and total_loaded >= max_samples:
                            break
                            
                    except Exception as e:
                        continue
                
                if max_samples and total_loaded >= max_samples:
                    break
                    
            except Exception as e:
                continue
        
        print(f"Loaded {len(self.all_images)} images")
    
    def __len__(self):
        return len(self.all_images)
    
    def __getitem__(self, idx):
        img = self.all_images[idx]
        if self.transform:
            img = self.transform(img)
        return img

# 网络定义
class SelfAttention(nn.Module):
    def __init__(self, in_dim):
        super(SelfAttention, self).__init__()
        self.query_conv = nn.Conv2d(in_dim, in_dim // 8, 1)
        self.key_conv = nn.Conv2d(in_dim, in_dim // 8, 1)
        self.value_conv = nn.Conv2d(in_dim, in_dim, 1)
        self.gamma = nn.Parameter(torch.zeros(1))
        self.softmax = nn.Softmax(dim=-1)
        
    def forward(self, x):
        batch_size, C, width, height = x.size()
        proj_query = self.query_conv(x).view(batch_size, -1, width * height).permute(0, 2, 1)
        proj_key = self.key_conv(x).view(batch_size, -1, width * height)
        energy = torch.bmm(proj_query, proj_key)
        attention = self.softmax(energy)
        proj_value = self.value_conv(x).view(batch_size, -1, width * height)
        out = torch.bmm(proj_value, attention.permute(0, 2, 1))
        out = out.view(batch_size, C, width, height)
        return self.gamma * out + x

class SimpleGenerator(nn.Module):
    def __init__(self, z_dim=128, ngf=64):
        super(SimpleGenerator, self).__init__()
        self.z_dim = z_dim
        self.main = nn.Sequential(
            nn.ConvTranspose2d(z_dim, ngf * 16, 4, 1, 0, bias=False),
            nn.BatchNorm2d(ngf * 16),
            nn.ReLU(True),
            nn.ConvTranspose2d(ngf * 16, ngf * 8, 4, 2, 1, bias=False),
            nn.BatchNorm2d(ngf * 8),
            nn.ReLU(True),
            nn.ConvTranspose2d(ngf * 8, ngf * 4, 4, 2, 1, bias=False),
            nn.BatchNorm2d(ngf * 4),
            nn.ReLU(True),
            nn.ConvTranspose2d(ngf * 4, ngf * 2, 4, 2, 1, bias=False),
            nn.BatchNorm2d(ngf * 2),
            nn.ReLU(True),
            nn.ConvTranspose2d(ngf * 2, ngf, 4, 2, 1, bias=False),
            nn.BatchNorm2d(ngf),
            nn.ReLU(True),
            SelfAttention(ngf),
            nn.ConvTranspose2d(ngf, ngf // 2, 4, 2, 1, bias=False),
            nn.BatchNorm2d(ngf // 2),
            nn.ReLU(True),
            nn.ConvTranspose2d(ngf // 2, 3, 4, 2, 1, bias=False),
            nn.Tanh()
        )
        
    def forward(self, z):
        z = z.view(z.size(0), z.size(1), 1, 1)
        return self.main(z)

class SimpleDiscriminator(nn.Module):
    def __init__(self, nc=3, ndf=64):
        super(SimpleDiscriminator, self).__init__()
        self.main = nn.Sequential(
            nn.Conv2d(nc, ndf, 4, 2, 1, bias=False),
            nn.LeakyReLU(0.2, True),
            nn.Conv2d(ndf, ndf * 2, 4, 2, 1, bias=False),
            nn.BatchNorm2d(ndf * 2),
            nn.LeakyReLU(0.2, True),
            nn.Conv2d(ndf * 2, ndf * 4, 4, 2, 1, bias=False),
            nn.BatchNorm2d(ndf * 4),
            nn.LeakyReLU(0.2, True),
            nn.Conv2d(ndf * 4, ndf * 8, 4, 2, 1, bias=False),
            nn.BatchNorm2d(ndf * 8),
            nn.LeakyReLU(0.2, True),
            SelfAttention(ndf * 8),
            nn.Conv2d(ndf * 8, ndf * 16, 4, 2, 1, bias=False),
            nn.BatchNorm2d(ndf * 16),
            nn.LeakyReLU(0.2, True),
            nn.Conv2d(ndf * 16, ndf * 32, 4, 2, 1, bias=False),
            nn.BatchNorm2d(ndf * 32),
            nn.LeakyReLU(0.2, True),
            nn.AdaptiveAvgPool2d(1),
            nn.Flatten(),
            nn.Linear(ndf * 32, 1)
        )
        
    def forward(self, x):
        return self.main(x).squeeze()

def train_with_fid_evaluation():
    set_seeds()
    
    celeba_path = "./celebA256"
    transform = transforms.Compose([
        transforms.RandomHorizontalFlip(0.5),
        transforms.ToTensor(),
        transforms.Normalize((0.5, 0.5, 0.5), (0.5, 0.5, 0.5))
    ])

    # 加载数据
    print("Loading datasets...")
    train_dataset = CelebA256ParquetDataset(celeba_path, transform=transform, split='train', max_samples=15000)  # 限制样本数用于快速训练
    train_dataloader = DataLoader(train_dataset, batch_size=16, shuffle=True, num_workers=2)
    
    # 用于FID计算的验证集
    val_dataset = CelebA256ParquetDataset(celeba_path, transform=transform, split='validation', max_samples=1000)  # 用1000个样本计算FID
    val_dataloader = DataLoader(val_dataset, batch_size=16, shuffle=False, num_workers=2)

    # 初始化模型
    generator = SimpleGenerator(z_dim=128, ngf=64).to(device)
    discriminator = SimpleDiscriminator(nc=3, ndf=64).to(device)

    # 优化器
    g_optimizer = optim.Adam(generator.parameters(), lr=0.0002, betas=(0.5, 0.999))
    d_optimizer = optim.Adam(discriminator.parameters(), lr=0.0002, betas=(0.5, 0.999))

    adversarial_loss = nn.BCEWithLogitsLoss()

    # 记录FID历史
    fid_history = []
    epoch_history = []

    def train_epoch(epoch, num_epochs):
        generator.train()
        discriminator.train()
        
        for i, batch in enumerate(train_dataloader):
            real_imgs = batch.to(device)
            batch_size = real_imgs.size(0)
            
            real_labels = torch.ones(batch_size).to(device) * 0.9
            fake_labels = torch.zeros(batch_size).to(device) * 0.1
            
            # 训练判别器
            d_optimizer.zero_grad()
            real_pred = discriminator(real_imgs)
            d_real_loss = adversarial_loss(real_pred, real_labels)
            
            z = torch.randn(batch_size, 128).to(device)
            fake_imgs = generator(z).detach()
            fake_pred = discriminator(fake_imgs)
            d_fake_loss = adversarial_loss(fake_pred, fake_labels)
            
            d_loss = (d_real_loss + d_fake_loss) / 2
            d_loss.backward()
            d_optimizer.step()
            
            # 训练生成器
            g_optimizer.zero_grad()
            z = torch.randn(batch_size, 128).to(device)
            gen_imgs = generator(z)
            gen_pred = discriminator(gen_imgs)
            g_loss = adversarial_loss(gen_pred, real_labels)
            
            g_loss.backward()
            g_optimizer.step()
            
            if i % 50 == 0:
                print(f"[Epoch {epoch}/{num_epochs}] [Batch {i}/{len(train_dataloader)}] D: {d_loss.item():.4f} G: {g_loss.item():.4f}")

    # 训练循环
    num_epochs = 50
    print(f"Starting training for {num_epochs} epochs with FID evaluation...")
    
    for epoch in range(num_epochs):
        print(f"\n{'='*60}")
        print(f"Epoch {epoch}/{num_epochs}")
        print(f"{'='*60}")
        
        # 训练一个epoch
        train_epoch(epoch, num_epochs)
        
        # 每5个epoch计算一次FID
        if epoch % 5 == 0 or epoch == num_epochs - 1:
            print("Calculating FID score...")
            try:
                fid_score = calculate_fid_score(generator, val_dataloader, num_samples=500, batch_size=16, device=device)
                fid_history.append(fid_score)
                epoch_history.append(epoch)
                
                print(f"FID at epoch {epoch}: {fid_score:.2f}")
                
                # 保存FID历史
                np.savez('fid_history.npz', epochs=epoch_history, fids=fid_history)
                
            except Exception as e:
                print(f"Error calculating FID: {e}")
                fid_history.append(float('inf'))
                epoch_history.append(epoch)
        
        # 保存样本和模型
        generator.eval()
        with torch.no_grad():
            z = torch.randn(16, 128).to(device)
            gen_imgs = generator(z) * 0.5 + 0.5
            torchvision.utils.save_image(gen_imgs, f'./saganOutput/sagan_epoch_{epoch:02d}.png', nrow=4)
        
        if epoch % 10 == 0:
            torch.save({
                'generator': generator.state_dict(),
                'discriminator': discriminator.state_dict(),
                'epoch': epoch,
                'fid_history': fid_history
            }, f'sagan_checkpoint_epoch_{epoch:02d}.pth')
        
        generator.train()

    print("\n Training completed!")
    
    # 绘制FID曲线
    if len(fid_history) > 0:
        plt.figure(figsize=(10, 6))
        plt.plot(epoch_history, fid_history, 'b-o', linewidth=2, markersize=8)
        plt.xlabel('Epoch')
        plt.ylabel('FID Score')
        plt.title('FID Score vs Training Epoch')
        plt.grid(True, alpha=0.3)
        plt.savefig('fid_progress.png', dpi=300, bbox_inches='tight')
        plt.show()
        
        print(f"Final FID Score: {fid_history[-1]:.2f}")
        print("FID progress plot saved as 'fid_progress.png'")
    
    # 保存最终模型
    torch.save({
        'generator': generator.state_dict(),
        'discriminator': discriminator.state_dict(),
        'fid_history': fid_history
    }, 'sagan_final.pth')

if __name__ == '__main__':
    try:
        train_with_fid_evaluation()
    except Exception as e:
        print(f"Training failed: {e}")
        import traceback
        traceback.print_exc()