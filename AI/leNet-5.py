import keras as kr
from keras import datasets, layers, models
import matplotlib.pyplot as plt
import numpy as np

# 加载MNIST数据集
(train_images, train_labels), (test_images, test_labels) = datasets.mnist.load_data()

"""
数据预处理函数

该函数对输入的图像数据进行预处理,包括在每个图像边缘添加零填充以调整尺寸至32x32,
随后进行图像尺寸重塑和数值归一化。

参数:
images - 输入的图像数据,预期为一个二维数组,其中每行代表一个图像的像素值。

返回值:
预处理后的图像数据,为一个四维数组,shape为(N, 32, 32, 1),其中N为图像数量,
32x32代表每个图像的尺寸(原始尺寸经padding处理后),最后的1代表通道数(灰度图像)。
图像的像素值被归一化到0到1之间。
"""
def preprocess_images(images):
    # 在图像每个边缘添加零填充以调整尺寸至32x32
    padded_images = np.pad(images, ((0, 0), (2, 2), (2, 2)), mode='constant')
    # 重塑图像数据维度,并进行归一化处理
    return padded_images.reshape((-1, 32, 32, 1)).astype('float32') / 255

train_images = preprocess_images(train_images)
test_images = preprocess_images(test_images)

# 将标签转换为one-hot编码
train_labels = kr.utils.to_categorical(train_labels)
test_labels = kr.utils.to_categorical(test_labels)

# 构建LeNet-5模型
def create_lenet5(input_shape: tuple) -> models.Sequential:
    """
    创建一个经典的LeNet-5卷积神经网络模型。
    
    参数:
    input_shape: tuple - 输入图像的形状,不包括批量大小维度。
    
    返回值:
    models.Sequential - 构建好的LeNet-5模型实例。
    """
    mod = models.Sequential()  # 初始化一个序贯模型
    mod.add(layers.Conv2D(6, (5, 5), activation='tanh', input_shape=input_shape, padding='same'))  # 第一个卷积层
    mod.add(layers.AveragePooling2D((2, 2), strides=2))  # 第一个平均池化层
    mod.add(layers.Conv2D(16, (5, 5), activation='tanh', padding='valid'))  # 第二个卷积层
    mod.add(layers.AveragePooling2D((2, 2), strides=2))  # 第二个平均池化层
    mod.add(layers.Conv2D(120, (5, 5), activation='tanh', padding='valid'))  # 第三个卷积层
    mod.add(layers.Flatten())  # 展平层,将卷积后的三维数据转换为一维
    mod.add(layers.Dense(84, activation='tanh'))  # 第一个全连接层
    mod.add(layers.Dense(10, activation='softmax'))  # 输出层,使用softmax激活函数
    mod.summary()  # 打印模型摘要
    return mod

model_input_shape = (32, 32, 1)
model = create_lenet5(model_input_shape)

# 编译模型
model.compile(optimizer='adam',
              loss='categorical_crossentropy',
              metrics=['accuracy'])


def train_model(models, train_image, train_label, test_image, test_label, epochs=10):
    """
    训练给定模型的函数。

    参数:
    - model: 需要训练的模型对象。
    - train_images: 训练用的图像数据集。
    - train_labels: 对应训练图像的标签数据集。
    - test_images: 测试用的图像数据集。
    - test_labels: 对应测试图像的标签数据集。
    - epochs: 训练的轮数,默认为10。

    返回值:
    - history: 训练过程中记录的历史信息。
    """
    try:
        # 训练模型并返回训练历史
        history = models.fit(train_image, train_label, epochs=epochs,
                            validation_data=(test_image, test_label))
        return history
    except Exception as e:
        # 如果训练过程中出现异常,打印错误信息
        print(f"模型训练错误: {e}")

history = train_model(model, train_images, train_labels, test_images, test_labels)

def evaluate_model(models, test_image, test_label):
    """
    评估给定模型在测试数据集上的性能。

    参数:
    - model: 训练好的模型,用于进行预测和评估。
    - test_images: 测试数据集中的图像数据,用于模型评估。
    - test_labels: 测试数据集中的标签数据,用于与模型预测结果进行比较。

    返回值:
    - test_acc: 测试数据集上的准确率。
    """
    try:
        # 在测试数据集上评估模型,返回测试损失和测试准确率
        test_loss, test_acc = models.evaluate(test_image, test_label, verbose=2)
        print(f"\n测试准确率: {test_acc}")  # 打印测试准确率
        return test_acc
    except Exception as e:
        # 处理在模型评估过程中可能发生的异常
        print(f"评估错误: {e}")

evaluate_model(model, test_images, test_labels)

def save_model(models, filepath):
    """
    保存模型到指定的文件路径。
    
    参数:
    model: 要保存的模型对象,必须具有save方法。
    filepath: 字符串,指定保存模型的文件路径。
    
    返回值:
    无
    """
    try:
        models.save(filepath)  # 尝试保存模型到指定路径
        print(f"文件成功保存到 {filepath}")
    except Exception as e:
        print(f"保存错误: {e}")  # 捕获并打印保存模型时可能出现的异常

model_save_path="./leNet-5.h5"
save_model(model, model_save_path)

#可视化训练过程
def plot_training_history(history):
    """
    可视化训练过程中的损失和准确率变化。
    
    参数:
    history: 训练过程中记录的历史信息,包含训练损失和准确率。
    
    返回值:
    无
    """
    # 绘制训练集和验证集的准确率曲线
    plt.plot(history.history['accuracy'], label='accuracy')
    plt.plot(history.history['val_accuracy'], label='val_accuracy')
    # 设置x轴和y轴的标签
    plt.xlabel('Epoch')
    plt.ylabel('Accuracy')
    # 设置y轴的显示范围
    plt.ylim([0.8, 1])
    # 添加图例并显示图形
    plt.legend(loc='lower right')
    plt.show()

plot_training_history(history)

def predict_and_show(image):
    """
    使用模型对给定图像进行预测,并显示预测结果及图像。
    
    参数:
    image : numpy数组
        输入的待预测图像,应为32x32大小的单通道图像。
        
    返回值:
    无
    """
    # 使用模型进行预测,输出为概率分布
    prediction = model.predict(image.reshape(1, 32, 32, 1))
    # 显示输入图像
    plt.imshow(image.reshape(32,32), cmap=plt.cm.binary)
    # 添加预测结果作为图像标题
    plt.title(f"prediction: {np.argmax(prediction)}")
    # 显示图像及标题
    plt.show(block=False)
    plt.pause(0)
    plt.close()

# 从测试集中选择图片进行预测
for i in range(20):
    predict_and_show(test_images[i])
