import torch.autograd
import torch.nn as nn
from torch.autograd import Variable
from torchvision import transforms
from torchvision import datasets
from torchvision.utils import save_image
import os

# 创建文件夹
if not os.path.exists('./mnistOutput'):
    os.mkdir('./mnistOutput')


def to_img(x):
    out = 0.5 * (x + 1)
    out = out.clamp(0, 1)  # Clamp函数可以将随机变化的数值限制在一个给定的区间[min, max]内：
    out = out.view(-1, 1, 28, 28)  # view()函数作用是将一个多行的Tensor,拼接成一行
    return out


batch_size = 128
num_epoch = 100
z_dimension = 50
# MNIST图像预处理
img_transform = transforms.Compose([
    transforms.ToTensor(),
    transforms.Normalize((0.1,), (0.5,))
])
# 数据集下载
mnist = datasets.MNIST(
    root='./data/', train=True, transform=img_transform, download=True
)
# 数据载入
dataloader = torch.utils.data.DataLoader(
    dataset=mnist, batch_size=batch_size, shuffle=True
)

###### 定义判别器  Discriminator###### 使用多层网络来作为判别器
# 将图片28x28展开成784，然后通过多层感知器，中间经过斜率设置为0.2的LeakyReLU激活函数，
# 最后接sigmoid激活函数得到一个0到1之间的概率进行二分类。
class discriminator(nn.Module):
    def __init__(self):
        super(discriminator, self).__init__()
        self.dis = nn.Sequential(
            nn.Linear(784, 512),
            nn.BatchNorm1d(512),
            nn.LeakyReLU(0.2),
            nn.Linear(512, 256),
            nn.BatchNorm1d(256),
            nn.LeakyReLU(0.2),
            nn.Linear(256, 1),
            nn.Sigmoid()
        )

    def forward(self, x):
        x = self.dis(x)
        return x


####### 定义生成器 Generator #####
# 输入一个50维的0～1之间的高斯分布，然后通过第一层线性变换将其映射到256维,
# 然后通过LeakyReLU激活函数，接着进行一个线性变换，再经过一个LeakyReLU激活函数，
# 然后经过线性变换将其变成784维，最后经过Tanh激活函数是希望生成的假的图片数据分布
# 能够在-1～1之间。
class generator(nn.Module):
    def __init__(self):
        super(generator, self).__init__()
        self.gen = nn.Sequential(
            nn.Linear(50, 128),
            nn.LeakyReLU(0.2),
            nn.Linear(128, 256),
            nn.BatchNorm1d(256),
            nn.LeakyReLU(0.2),
            nn.Linear(256, 512),
            nn.BatchNorm1d(512),
            nn.LeakyReLU(0.2),
            nn.Linear(512, 1024),
            nn.BatchNorm1d(1024),
            nn.LeakyReLU(0.2),
            nn.Linear(1024, 784),
            nn.Tanh()
        )

    def forward(self, x):
        x = self.gen(x)
        return x
#########判别器训练train#####################
# 分为两部分：1、真的图像判别为真；2、假的图像判别为假
# 此过程中，生成器参数不断更新
# 首先需要定义loss的度量方式  （二分类的交叉熵）
# 其次定义 优化函数,优化函数的学习率为0.0003
# 创建对象
D = discriminator()
G = generator()
if torch.cuda.is_available():
    D = D.cuda()
    G = G.cuda()
criterion = nn.BCELoss()
d_optimizer = torch.optim.Adam(D.parameters(), lr=0.0003)
g_optimizer = torch.optim.Adam(G.parameters(), lr=0.0003)
###########################进入训练##判别器的判断过程#####################
for epoch in range(num_epoch):  # 进行多个epoch的训练
    for i, (img, _) in enumerate(dataloader):
        num_img = img.size(0)
        # =============================训练判别器==================
        img = img.view(num_img, -1)
        real_img = Variable(img).cuda()
        real_label = Variable(torch.ones(num_img, 1)).cuda()
        fake_label = Variable(torch.zeros(num_img, 1)).cuda()
        # 计算真实图片的损失
        real_out = D(real_img)
        d_loss_real = criterion(real_out, real_label)
        real_scores = real_out
        # 计算假的图片的损失
        z = Variable(torch.randn(num_img, z_dimension)).cuda()
        fake_img = G(z)
        fake_out = D(fake_img)
        d_loss_fake = criterion(fake_out, fake_label)
        fake_scores = fake_out
        # 损失函数和优化
        d_loss = d_loss_real + d_loss_fake
        d_optimizer.zero_grad()
        d_loss.backward()
        d_optimizer.step()
        # ==================训练生成器============================
        z = Variable(torch.randn(num_img, z_dimension)).cuda()
        fake_img = G(z)
        output = D(fake_img)
        g_loss = criterion(output, real_label)
        g_optimizer.zero_grad()
        g_loss.backward()
        g_optimizer.step()

        # 打印中间的损失
        if (i + 1) % 100 == 0: # 60000 / 128 = 468 /100 = 4 次
            print('Epoch[{}/{}],d_loss:{:.6f},g_loss:{:.6f} '
                  'D real: {:.6f},D fake: {:.6f}'.format(
                epoch, num_epoch, d_loss.data.item(), g_loss.data.item(),
                real_scores.data.mean(), fake_scores.data.mean()  # 打印的是真实图片的损失均值
            ))
        if epoch == 0 and i==len(dataloader)-1:
            real_images = to_img(real_img.cuda().data)
            save_image(real_images, './mnistOutput/real_images.png')
        if i==len(dataloader)-1:
            fake_images = to_img(fake_img.cuda().data)
            save_image(fake_images, './mnistOutput/fake_images-{}.png'.format(epoch + 1))
# 保存模型
torch.save(G.state_dict(), './generator.pth')
torch.save(D.state_dict(), './discriminator.pth')

