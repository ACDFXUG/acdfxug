# code for a 3-layer neural network,and code for learning the MNIST daeaset
# 一个用于MNIST数字识别的三层神经网络程序（输入层，隐藏层，输出层）

from turtle import forward
import numpy as np  # 用于进行数组和矩阵的运算操作
import scipy.special as ssp  # 里面有激活函数sigmoid函数，可以直接调用
# import pyplot as plt
# from matplotlib import pyplot as plt
import matplotlib
import matplotlib.pyplot as plt
from sqlalchemy import null  # 用于进行数字矩阵的图像输出，该函数库时进行图像处理常用到的函数库
# %matplotlib inline

# neural network class definition
# 制作一个神经网络算法的类，其名为神经网络，相当于函数库，直接进行调用里面的函数即可。
class neuralNetwork:
    # initialise the neural network
    # 对神经网络的参数进行初始化
    def __init__(self, inputnodes, hiddennodes, outputnodes, learningrate): # 用于类的初始值的设定
        # set number of nodes in each input, hidden, output layer
        # 设置节输入层、隐藏层、输出层的节点数  （self表示类所自带的不能改变的内容）
        self.inodes = inputnodes # 输入层节点数
        self.hnodes = hiddennodes # 隐藏层节点数
        self.onodes = outputnodes # 输出层节点数
        
        # link weight matrices, wih and who
        # 设置输入层与隐藏层直接的权重关系矩阵以及隐藏层与输出层之间的权重关系矩阵
        # （一开始随机生成权重矩阵的数值，利用正态分布，均值为0，方差为隐藏层节点数的-0.5次方，）
        self.wih = np.random.normal(0.0, pow(self.hnodes, -0.5), (self.hnodes, self.inodes)) #矩阵大小为隐藏层节点数×输入层节点数
        self.who = np.random.normal(0.0, pow(self.hnodes, -0.5), (self.onodes, self.hnodes)) #矩阵大小为输出层节点数×隐藏层节点数
        
        # learning rate
        # 设置学习率α
        self.lr = learningrate
        
        # activation function is the sigmoid function
        # 将激活函数sigmoid定义为self.activation_function
        self.activation_function = lambda x: ssp.expit(x) 
        # lambda x:表示快速生成函数f(x) 并将其命名为self.activation_function
        
        pass
    
    def forward(self, inputs):
        # 计算隐藏层的输入信号
        hidden_inputs=np.dot(self.wih,inputs)
        # 计算隐藏层的输出信号
        hidden_outputs=self.activation_function(hidden_inputs)

        # 计算输出层的输入信号
        final_inputs=np.dot(self.who,hidden_outputs)
        # 计算输出层的输出信号
        final_outputs=self.activation_function(final_inputs)
        # 返回隐藏层输出信号和输出层输出信号
        return hidden_outputs, final_outputs
    def backward(self, inputs, targets, hidden_outputs, final_outputs):
        # 计算输出层的误差（目标值 - 实际值）
        output_errors=targets-final_outputs
        # 计算隐藏层的误差（输出层误差按权重分配到隐藏层节点）
        hidden_errors=np.dot(self.who.T,output_errors)

        # 更新隐藏层与输出层之间的权重
        self.who+=self.lr*np.dot((output_errors*final_outputs*(1.0-final_outputs)),np.transpose(hidden_outputs))

        # 更新输入层与隐藏层之间的权重
        self.wih+=self.lr*np.dot((hidden_errors*hidden_outputs*(1.0-hidden_outputs)),np.transpose(inputs))
        
    # train the neural network
    # 训练数据集所要用到的函数定义为：train()
    def train(self, inputs_list, targets_list):
        # convert inputs list to 2d array
        # 将导入的输入列表数据和正确的输出结果转换成二维矩阵
        inputs = np.array(inputs_list, ndmin = 2).T # array函数是矩阵生成函数，将输入的inputs_list转换成二维矩阵，ndmin=2表示二维矩阵
        targets = np.array(targets_list, ndmin = 2).T # .T表示矩阵的转置，生成后的矩阵的转置矩阵送入变量targets
        hidden_outputs, final_outputs = self.forward(inputs)
        self.backward(inputs, targets, hidden_outputs, final_outputs)
       
        pass
    
    # query the nerual network
    # 查询函数，用于在训练算法完成训练之后检验训练所得的权重矩阵是否准确
    def query(self, inputs_list):
        # convert inputs lst to 2d array
        # 将输入的测试集数据转换成二维矩阵
        inputs = np.array(inputs_list, ndmin = 2).T

        # 以下程序为计算输出结果的程序，与上面前向传播算法一致（到 return final_outputs结束）
        # calculate signals into hidden layer
        hidden_inputs = np.dot(self.wih, inputs)
        # calculate the signals emerging from hidden layer
        hidden_outputs = self.activation_function(hidden_inputs)

        # calculate signal into final output layer
        final_inputs = np.dot(self.who, hidden_outputs)
        # calculate the signals emerging from final output layer
        final_outputs = self.activation_function(final_inputs)

        return final_outputs
# 神经网络算法的类定义完毕

# number of input, hidden and output nodes
# 设置输入节点数、隐藏层节点数、输出节点数
input_nodes = 784 # 因为有784个像素值（28×28），所以相当于输入有784个
hidden_nodes = 200  # 隐藏层节点数设置为200，可以随意设置，理论上节点数越多，得到的算法准确率越高
# 实际上达到一定值后将会基本不变，而且节点数越多，训练算法所需花费时间越长，因此节点数不宜设置的过多
output_nodes = 10 # 因为从0到9一共十个数，所以输出节点数为10

# learning rate
learning_rate = 0.1 # 学习率设置为0.1，可以随意设置，但是经过测试，当为0.1时，得到的算法准确率最高

# create instance of neural network
# 建立神经网络的类，取名为"n"，方便后面使用
n = neuralNetwork(input_nodes, hidden_nodes, output_nodes, learning_rate)


# load the mnist training data CSV file into a list
# 将mnist_train.csv文件导入
training_data_file = open("mnist_train.csv", 'r') # ‘r’表示只读
training_data_list = training_data_file.readlines()
training_data_file.close()

# train the neural network
# 利用导入的数据训练神经网络算法

epochs = 5
# epochs为世代，让算法循环5次

for e in range(epochs):
    # go through all records in the training data set
    # 遍历所有输入的数据
    for record in training_data_list:
        # split the record by the ',' commas
        # 将所有数据通过逗号分隔开
        all_values = record.split(',')
        # scale and shift the inputs
        # 对输入的数据进行处理，取后784个数据除以255，再乘以0.99，最后加上0。01，是所有的数据都在0.01到1.00之间
        inputs = (np.asfarray(all_values[1:]) / 255.0 * 0.99) + 0.01
        # create the target output values (all 0.01, except the desired label which is 0.99)
        # 建立准确输出结果矩阵，对应的位置标签数值为0.99，其他位置为0.01
        targets = np.zeros(output_nodes) + 0.01
        # all_values[0] is the target label for this record
        targets[int(all_values[0])] = 0.99
        n.train(inputs, targets) # 利用训练函数训练神经网络
        pass
    
    pass

# load the mnist test data CSV file into a list
# 导入测试集数据
test_data_file = open("mnist_test.csv", 'r')
test_data_list = test_data_file.readlines()
test_data_file.close()

# test the neural network
# 用query函数对测试集进行检测
# go through all the records in the test data set for record in the test_data_list:
scorecard = 0 # 得分卡，检测对一个加一分

for record in test_data_list:
    # split the record by the ',' comas
    # 将所有测试数据通过逗号分隔开
    all_values = record.split(',')
    # correct answer is first value
    # 正确值为每一条测试数据的第一个数值
    correct_label = int(all_values[0])
    print("correct label", correct_label) # 将正确的数值在屏幕上打印出来
    # scale and shift the inputs
    # 对输入数据进行处理，取后784个数据除以255，再乘以0.99，最后加上0。01，是所有的数据都在0.01到1.00之间
    inputs = (np.asfarray(all_values[1:]) / 255.0 * 0.99) + 0.01
    # query the network
    # 用query函数对测试集进行检测
    outputs = n.query(inputs)
    # the index of the highest value corresponds to out label
    # 得到的数字就是输出结果的最大的数值所对应的标签
    label = np.argmax(outputs) # argmax()函数用于找出数值最大的值所对应的标签
    print("Output is ", label) # 在屏幕上打出最终输出的结果
    
    # output image of every digit
    # 输出每一个数字的图片
    # image_correct = np.asfarray(all_values[1:]).reshape((28, 28))
    # plt.imshow(image_correct, cmap = 'Greys', interpolation = 'None')
    # plt.show()

    # append correct or incorrect to list
    if (label == correct_label):
        # network's answer matchs correct answer, add 1 to scorecard
        scorecard += 1
    else:
        # network's answer doesn't match correct answer, add 0 to scorecard
        scorecard += 0
        pass
    pass

# calculate the performance score, the fraction
# 计算准确率 得分卡最后的数值/10000（测试集总个数）
print("performance = ", scorecard / 10000)
