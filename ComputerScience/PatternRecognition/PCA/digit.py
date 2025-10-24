# 1. 导入所需要的库和模块
from sklearn.datasets import load_digits
from sklearn.decomposition import PCA
from sklearn.manifold import TSNE
import matplotlib.pyplot as plt
import numpy as np
from matplotlib import cm

# 2. 导入数据，探索数据
digits = load_digits()
print(digits.data.shape) # (1797, 64)
print(set(digits.target.tolist())) # {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}

# 3. 定义画图函数
def plot_digits(data, name): 
    # 创建一个包含多个子图的图像，并在每个子图上显示一幅图像数据。
    # plt.subplots(4, 10) 创建一个 4 行 10 列的子图网格。
        # figsize=(10, 4) 设置整个图像的大小为 10 英寸宽，4 英寸高。
        # subplot_kw={"xticks": [], "yticks": []} 去除每个子图的 x 轴和 y 轴刻度。 
    fig, axes = plt.subplots(4,10,figsize=(10,4)
                            ,subplot_kw = {"xticks":[],"yticks":[]}
                            )
    # enumerate(axes.flat) 遍历所有子图。flat 属性将二维数组展平成一维数组。
    # ax.imshow(data[i].reshape(8, 8), cmap="binary") 在每个子图上绘制图像数据。
        # data[i] 获取第 i 个图像数据。
        # reshape(8, 8) 将一维数据重塑为 8x8 的二维数组。
        # cmap="binary" 使用黑白颜色映射来显示图像。
    for i, ax in enumerate(axes.flat):
        ax.imshow(data[i].reshape(8,8),cmap="binary")
    # 保存图片
    plt.savefig('images/'+name+'.png', dpi=300)

# 调用函数并保存图像
plot_digits(digits.data, 'origin digit')

# 4. 为数据加上噪音
# 初始化一个随机数生成器，种子值为 42。这确保每次运行代码时都能得到相同的随机数序列，便于调试和复现结果。
np.random.RandomState(42)
# 为 digits.data 中的每个元素加上一个均值为 0、标准差为 2 的正态分布随机数。
noisy = np.random.normal(digits.data,2) 
plot_digits(noisy, 'noise digit')

# 5. PCA降维
# PCA(0.5) 初始化一个 PCA 模型，其中 0.5 表示保留至少 50% 的方差。
# fit(noisy) 训练 PCA 模型，使用加噪后的数据 noisy。
pca = PCA(0.5).fit(noisy)
# transform(noisy) 应用训练好的 PCA 模型对 noisy 数据进行降维变换。
X_dr = pca.transform(noisy)
print(X_dr.shape) # (1797, 6)

# 6. 逆转降维结果，实现降噪
# inverse_transform 方法将降维后的数据 X_dr 逆变换回原始特征空间。从低维空间恢复到高维空间，从而尝试去除噪声。
without_noise = pca.inverse_transform(X_dr)
plot_digits(without_noise, 'denoised digit')
print(without_noise.shape) # (1797, 64)

def plot_pca_results(X, labels, title):
    '''
        利用PCA将图像降至2维
    '''
    plt.figure(figsize=(8, 6))
    scatter = plt.scatter(pca_proj[:, 0], pca_proj[:, 1], c=labels, cmap='viridis', alpha=0.7)
    plt.colorbar(scatter)
    plt.xlabel('Principal Component 1')
    plt.ylabel('Principal Component 2')
    plt.title(title)
    plt.savefig('images/'+title+'.png')

# 使用 pca 对原始数据进行可视化
plot_pca_results(digits.data, digits.target, 'PCA Visualization of Original Digits Data')

# 使用 pca 对加噪后的数据进行可视化
plot_pca_results(noisy, digits.target, 'PCA Visualization of Noisy Digits Data')

# 使用 pca 对去噪后的数据进行可视化
plot_pca_results(without_noise, digits.target, 'PCA Visualization of Digits Data')

def plot_tsne_results(X, labels, title):
    '''
        利用t-SNE将图像降至2维并进行可视化
    '''
    plt.savefig('images/'+title+'.png')

'''
使用 t-SNE 对原始数据、加噪后的数据、去噪后的数据进行可视化。
'''