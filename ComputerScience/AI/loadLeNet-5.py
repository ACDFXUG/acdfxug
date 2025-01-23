import keras as kr
import numpy as np
import matplotlib.pyplot as plt
from keras import datasets

(train_images, train_labels), (test_images, test_labels) = datasets.mnist.load_data()

def preprocess_images(images):
    # 在图像每个边缘添加零填充以调整尺寸至32x32
    padded_images = np.pad(images, ((0, 0), (2, 2), (2, 2)), mode='constant')
    # 重塑图像数据维度,并进行归一化处理
    return padded_images.reshape((-1, 32, 32, 1)).astype('float32') / 255

train_images=preprocess_images(train_images)
test_images=preprocess_images(test_images)


# 将标签转换为one-hot编码
train_labels = kr.utils.to_categorical(train_labels)
test_labels = kr.utils.to_categorical(test_labels)


model=kr.models.load_model("./leNet-5.h5")

def evaluate_model(mod, test_image, test_label)->None:
    """
    评估给定模型在测试数据集上的性能。
    
    参数:
    mod -- 训练好的模型，用于进行测试评估。
    test_image -- 测试数据集的图像部分，用于模型的输入。
    test_label -- 测试数据集的标签部分，用于评估模型的输出。
    
    返回值:
    None
    """
    # 计算模型在测试数据集上的损失和准确率
    test_loss, test_acc = mod.evaluate(test_image, test_label, verbose=2)
    # 打印测试准确率和损失
    print(f'测试准确率: {test_acc * 100:.2f}%')
    print(f'测试损失值: {test_loss}')

evaluate_model(model, test_images, test_labels)

def predict_and_show(image)->None:
    """
    使用模型对给定图像进行预测,并显示图像及预测结果。
    
    参数:
    image : numpy数组
        输入的待预测图像,应为32x32大小的单通道图像。
        
    返回值:
    无
    """
    # 使用模型进行预测
    prediction = model.predict(image.reshape(1, 32, 32, 1))
    # 输出预测结果到控制台
    print(f"预测值: {np.argmax(prediction)}")
    # 显示输入图像
    plt.imshow(image.reshape(32, 32), cmap=plt.cm.binary)
    plt.show(block=False)
    plt.pause(0.1)
    plt.close()

print(f"测试集的数量: {len(test_images)}")
for i in range(10):
    predict_and_show(test_images[i])