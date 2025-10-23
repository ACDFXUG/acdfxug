import numpy as np
import matplotlib.pyplot as plt
from sklearn import svm
import pandas as pd

# -------------------------------------------- iris -------------------------------------------
# 从文件src\iris.txt读取鸢尾花数据集到DataFrame对象iris中
iris = pd.read_csv("src/iris.txt", header=0)
# 提取数据集中的值，并将其转换为NumPy数组iris_data
iris_data=iris.values[0:150,0:6]
iris_data=np.array(iris_data[0:150,0:6])
# 从iris_data中选取样本构建训练集iris_train_data
# 分别选择第0-29行、50-79行、100-129行的数据，将这些数据堆叠起来形成训练集
iris_train_data=iris_data[range(0,30),0:6]
iris_train_data=np.vstack((iris_train_data,iris_data[range(50,80),0:6]))
iris_train_data=np.vstack((iris_train_data,iris_data[range(100,130),0:6]))
iris_train_data=np.array(iris_train_data)
# 从训练集中提取标签iris_train_label和特征iris_train_data
iris_train_label=iris_train_data[:,4]
iris_train_data=iris_train_data[:,0:4]
# 将特征数据类型转换为float64
iris_train_data=iris_train_data.astype('float64')
print(iris_train_data.shape)
print(iris_train_label.shape)

# 测试集
iris_test_data=iris_data[range(30,50),0:6]
iris_test_data=np.vstack((iris_test_data,iris_data[range(80,100),0:6]))
iris_test_data=np.vstack((iris_test_data,iris_data[range(130,149),0:6]))
iris_test_data=np.array(iris_test_data)
iris_test_label=iris_test_data[:,4]
iris_test_data=iris_test_data[:,0:4]
iris_test_data=iris_test_data.astype('float64')
# iris_test_label=iris_test_label.astype('float64')
print(iris_test_data.shape)
print(iris_test_label.shape)
test_acc = []
val_acc = []
# 通过调整SVM模型的C参数，使用线性核函数训练模型，并计算每次训练在训练集和测试集上的准确率
for num in range(1,11):
    # 模型搭建。循环设置不同的C值（0.1到1.0）
    clf = svm.SVC(C=num/10, kernel='linear', decision_function_shape='ovr')
    # 模型训练
    rf = clf.fit(iris_train_data, iris_train_label)
    # 模型测试
    c = clf.score(iris_train_data,iris_train_label)
    print("C值:", num/10, "交叉验证验证准确率：", c)
    val_acc.append(c)   
    c = clf.score(iris_test_data,iris_test_label)
    print("C值:", num/10, "测试集准确率：", c)
    test_acc.append(c)
plt.figure(4)
plt.subplot(1,2,1)
plt.plot(range(1,11),val_acc)
plt.grid()
plt.xlabel('C/10')
plt.ylabel('acc')
plt.subplot(1,2,2)
plt.plot(range(1,11),test_acc)
plt.grid()
plt.xlabel('C/10')
plt.ylabel('acc')
plt.show()