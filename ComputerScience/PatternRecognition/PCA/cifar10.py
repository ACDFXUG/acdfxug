import numpy as np
import matplotlib.pyplot as plt
from sklearn.decomposition import PCA
from sklearn.manifold import TSNE
from sklearn.preprocessing import StandardScaler
import pickle
import os

def load_cifar10_test_data(data_dir='./cifar10'):
    """
    加载CIFAR-10测试数据。
    :param data_dir: CIFAR-10数据集所在的目录路径。
    :return: (data, labels), 其中 data 是形状为 (10000, 3072) 的数组,labels 是长度为10000的列表。
    """
    test_batch_path = os.path.join(data_dir, 'test_batch')
    with open(test_batch_path, 'rb') as f:
        # Python 3 需要指定 encoding='bytes'
        batch = pickle.load(f, encoding='bytes')

    # 提取数据和标签
    data = batch[b'data']  # 形状: (10000, 3072)
    labels = batch[b'labels']  # 形状: (10000,)

    return data, labels

def plot_images(images, labels, class_names, title, save_path=None):
    """
    绘制图像网格。
    :param images: 图像数据,形状为 (N, 3072)。
    :param labels: 图像对应的标签列表。
    :param class_names: 类别名称列表。
    :param title: 图像标题。
    :param save_path: 保存路径,如果为None则不保存。
    """
    n = min(25, len(images))  # 最多显示25张图
    fig, axes = plt.subplots(5, 5, figsize=(10, 10))
    fig.suptitle(title, fontsize=16)

    for i in range(n):
        ax = axes[i // 5, i % 5]
        # 修复图像重塑逻辑
        if images.shape[1] == 3072:  # 如果是扁平化的数据
            # 1. 先重塑为 (3, 32, 32)
            img_reshaped = images[i].reshape(3, 32, 32)
            # 2. 再转置为 (32, 32, 3),这是imshow期望的格式
            img = np.transpose(img_reshaped, (1, 2, 0))
        else:  # 如果已经是 (32, 32, 3) 形状
            img = images[i]

        ax.imshow(img.astype(np.uint8))
        ax.set_title(class_names[labels[i]], fontsize=10)
        ax.axis('off')

    plt.tight_layout()
    if save_path:
        plt.savefig(save_path, dpi=300, bbox_inches='tight')
    plt.show()

def add_noise(images, noise_level=0.1):
    """
    为图像添加高斯噪声。
    :param images: 输入图像数据,形状为 (N, 3072)。
    :param noise_level: 噪声水平,标准差。
    :return: 添加噪声后的图像。
    """
    # 生成与图像形状相同的高斯噪声
    noise = np.random.normal(loc=0.0, scale=noise_level * 255, size=images.shape)
    # 将噪声加到图像上,并限制像素值在 [0, 255] 范围内
    noisy_images = np.clip(images + noise, 0, 255).astype(np.uint8)
    return noisy_images

def pca_denoise(noisy_images, n_components_list=[50, 100, 200]):
    """
    使用PCA对噪声图像进行降噪。
    :param noisy_images: 带噪声的图像数据,形状为 (N, 3072)。
    :param n_components_list: 保留的主成分数量列表。
    :return: 一个字典,键为n_components,值为降噪后的图像。
    """
    denoised_results = {}

    for n_comp in n_components_list:
        # 创建PCA对象
        pca = PCA(n_components=n_comp)
        # 在带噪声的数据上拟合PCA模型
        pca.fit(noisy_images)
        # 用前n_comp个主成分重构图像
        transformed = pca.transform(noisy_images)
        denoised = pca.inverse_transform(transformed)
        # 限制像素值在 [0, 255] 范围内
        denoised = np.clip(denoised, 0, 255).astype(np.uint8)
        denoised_results[n_comp] = denoised

    return denoised_results

def visualize_with_pca(data, labels, class_names, save_path=None):
    """
    使用PCA将数据降到2D进行可视化。
    :param data: 输入数据,形状为 (N, 3072)。
    :param labels: 数据对应的标签。
    :param class_names: 类别名称列表。
    :param save_path: 保存路径。
    """
    # --- 新增：标准化数据 ---
    scaler = StandardScaler()
    data_scaled = scaler.fit_transform(data)  # 对数据进行标准化

    pca = PCA(n_components=2)
    data_2d = pca.fit_transform(data_scaled)  # 对标准化后的数据进行PCA

    plt.figure(figsize=(10, 8))
    scatter = plt.scatter(data_2d[:, 0], data_2d[:, 1], c=labels, cmap='tab10', s=10)
    plt.title('PCA Visualization of CIFAR-10 (Standardized)')
    plt.xlabel('Principal Component 1')
    plt.ylabel('Principal Component 2')
    plt.legend(handles=scatter.legend_elements()[0], labels=class_names, loc='upper right')
    if save_path:
        plt.savefig(save_path, dpi=300, bbox_inches='tight')
    plt.show()

def visualize_with_tsne(data, labels, class_names, save_path=None, perplexity=30):
    """
    使用t-SNE将数据降到2D进行可视化。
    :param data: 输入数据,形状为 (N, 3072)。
    :param labels: 数据对应的标签。
    :param class_names: 类别名称列表。
    :param save_path: 保存路径。
    :param perplexity: t-SNE的困惑度参数。
    """
    # --- 新增：标准化数据 ---
    scaler = StandardScaler()
    data_scaled = scaler.fit_transform(data)  # 对数据进行标准化

    tsne = TSNE(n_components=2, perplexity=perplexity, random_state=42)
    data_2d = tsne.fit_transform(data_scaled)  # 对标准化后的数据进行t-SNE

    plt.figure(figsize=(10, 8))
    scatter = plt.scatter(data_2d[:, 0], data_2d[:, 1], c=labels, cmap='tab10', s=10)
    plt.title(f't-SNE Visualization (Perplexity={perplexity}, Standardized)')
    plt.xlabel('t-SNE Dimension 1')
    plt.ylabel('t-SNE Dimension 2')
    plt.legend(handles=scatter.legend_elements()[0], labels=class_names, loc='upper right')
    if save_path:
        plt.savefig(save_path, dpi=300, bbox_inches='tight')
    plt.show()

def create_visualizations(data, labels, class_names, prefix, save_dir='images'):
    """
    为给定的数据集生成PCA和t-SNE可视化图。
    :param data: 输入数据,形状为 (N, 3072)。
    :param labels: 数据对应的标签。
    :param class_names: 类别名称列表。
    :param prefix: 图片文件名前缀,用于区分不同数据集。
    :param save_dir: 保存图片的目录。
    """
    os.makedirs(save_dir, exist_ok=True)

    # 标准化数据
    scaler = StandardScaler()
    data_scaled = scaler.fit_transform(data)

    # PCA 可视化
    pca = PCA(n_components=2)
    data_2d = pca.fit_transform(data_scaled)

    plt.figure(figsize=(10, 8))
    scatter = plt.scatter(data_2d[:, 0], data_2d[:, 1], c=labels, cmap='tab10', s=10)
    plt.title(f'PCA Visualization - {prefix}')
    plt.xlabel('Principal Component 1')
    plt.ylabel('Principal Component 2')
    plt.legend(handles=scatter.legend_elements()[0], labels=class_names, loc='upper right')
    plt.savefig(os.path.join(save_dir, f'{prefix}_pca.png'), dpi=300, bbox_inches='tight')
    plt.show()

    # t-SNE 可视化 (使用默认perplexity)
    tsne = TSNE(n_components=2, perplexity=30, random_state=42)
    data_2d = tsne.fit_transform(data_scaled)

    plt.figure(figsize=(10, 8))
    scatter = plt.scatter(data_2d[:, 0], data_2d[:, 1], c=labels, cmap='tab10', s=10)
    plt.title(f't-SNE Visualization - {prefix} (Perplexity=30)')
    plt.xlabel('t-SNE Dimension 1')
    plt.ylabel('t-SNE Dimension 2')
    plt.legend(handles=scatter.legend_elements()[0], labels=class_names, loc='upper right')
    plt.savefig(os.path.join(save_dir, f'{prefix}_tsne_perp30.png'), dpi=300, bbox_inches='tight')
    plt.show()

    # t-SNE 可视化 (可选：尝试另一个perplexity)
    tsne = TSNE(n_components=2, perplexity=50, random_state=42)
    data_2d = tsne.fit_transform(data_scaled)

    plt.figure(figsize=(10, 8))
    scatter = plt.scatter(data_2d[:, 0], data_2d[:, 1], c=labels, cmap='tab10', s=10)
    plt.title(f't-SNE Visualization - {prefix} (Perplexity=50)')
    plt.xlabel('t-SNE Dimension 1')
    plt.ylabel('t-SNE Dimension 2')
    plt.legend(handles=scatter.legend_elements()[0], labels=class_names, loc='upper right')
    plt.savefig(os.path.join(save_dir, f'{prefix}_tsne_perp50.png'), dpi=300, bbox_inches='tight')
    plt.show()

def main():
    # 创建 images 文件夹（如果不存在）
    os.makedirs('images', exist_ok=True)

    # 1. 加载数据
    print("Loading CIFAR-10 test data...")
    data, labels = load_cifar10_test_data('./cifar10')
    class_names = ['airplane', 'automobile', 'bird', 'cat', 'deer',
                   'dog', 'frog', 'horse', 'ship', 'truck']

    # 2. 显示原始图像
    print("Displaying original images...")
    plot_images(data, labels, class_names, "Original CIFAR-10 Images",
                save_path='images/original.png')

    # 3. 添加噪声
    print("Adding noise to images...")
    noisy_data = add_noise(data.copy(), noise_level=0.1)  # 可以调整 noise_level
    plot_images(noisy_data, labels, class_names, "Noisy CIFAR-10 Images",
                save_path='images/noisy.png')

    # 4. PCA降噪
    print("Applying PCA for denoising...")
    n_components_to_test = [50, 100, 200]  # 可以尝试不同的值
    denoised_dict = pca_denoise(noisy_data, n_components_to_test)

    # 显示不同参数下的降噪效果
    for n_comp, denoised_images in denoised_dict.items():
        title = f"PCA Denoised (n_components={n_comp})"
        save_path = f'images/denoised_pca_{n_comp}.png'
        plot_images(denoised_images, labels, class_names, title, save_path)

    # 5. 为所有数据集生成可视化图 (这是新增的核心部分)
    print("Generating visualizations for Original, Noisy, and Denoised data...")

    # 为原始数据生成可视化
    create_visualizations(data, labels, class_names, "Original")

    # 为加噪数据生成可视化
    create_visualizations(noisy_data, labels, class_names, "Noisy")

    # 为降噪数据生成可视化 (这里我们选择 n_components=100 的结果作为代表)
    # 您也可以为每个降噪结果都生成可视化
    denoised_data_for_vis = denoised_dict[100]  # 选择100个主成分的结果
    create_visualizations(denoised_data_for_vis, labels, class_names, "Denoised_n100")

if __name__ == "__main__":
    main()