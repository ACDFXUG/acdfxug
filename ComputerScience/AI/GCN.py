import torch
from torch import nn,optim
import torch.nn.functional as F
from dgl.data import CoraGraphDataset
from dgl.nn.pytorch import GraphConv
import matplotlib.pyplot as plt

# 定义GCN层
class GCN(nn.Module):
    """
    定义一个GCN模型,包含两个图卷积层和一个全连接层。
    """
    def __init__(self, in_feats, hidden_size, num_classes):
        """
        初始化图卷积网络(Graph Convolutional Network, GCN)类。

        参数:
        in_feats: int - 输入特征的维度
        hidden_size: int - 隐藏层的特征维度
        num_classes: int - 输出类别数
        """
        super(GCN, self).__init__()
        # 定义第一个图卷积层，用于将输入特征转换为隐藏层特征
        self.conv1 = GraphConv(in_feats, hidden_size)
        # 定义第二个图卷积层，用于将隐藏层特征转换为输出类别数对应的特征
        self.conv2 = GraphConv(hidden_size, num_classes)

    def forward(self, g, features):
        """
        前向传播函数，用于处理给定的图和特征。
        参数:
        g -- 图对象，表示图的结构。
        features -- 图的特征矩阵，用于输入到卷积层。
        返回:
        x -- 经过两层卷积和激活函数处理后的特征矩阵。
        """
        # 进行第一层卷积操作
        x = self.conv1(g, features)
        # 应用ReLU激活函数，引入非线性
        x = torch.relu(x)
        # 进行第二层卷积操作
        x = self.conv2(g, x)
        # 再次应用ReLU激活函数
        return torch.relu(x)

# 加载CoraGraph数据集
def load_cora_graph_data():
    """
    加载Cora图数据集。
    
    该函数从CoraGraphDataset中获取第一个样本的图结构、特征、标签和训练掩码。
    这些数据集组件对于图神经网络的学习和评估至关重要。
    
    Returns:
        g (DGLGraph): Cora图的数据结构,包含了节点和边的信息。
        features (Tensor): 图中节点的特征向量,用于模型训练。
        labels (Tensor): 图中节点的标签,用于模型的分类任务。
        train_mask (Tensor): 训练掩码,指示哪些节点用于训练,哪些用于验证或测试。
    """
    # 从CoraGraphDataset加载数据集
    data = CoraGraphDataset()
    # 获取数据集的第一个样本,即Cora图
    g = data[0]
    # 提取图中节点的特征数据
    features = g.ndata['feat']
    # 提取图中节点的标签数据
    labels = g.ndata['label']
    # 提取图中节点的训练掩码数据
    train_mask = g.ndata['train_mask']
    # 返回图结构、特征、标签和训练掩码
    return g, features, labels, train_mask



# 训练函数
def train(g, model, features, labels, mask, loss_fcn, optimizer):
    """
    在给定的图数据上训练模型。

    参数:
    g -- 图数据结构,包含节点和边的信息。
    model -- 待训练的模型,用于在图数据上进行预测。
    features -- 节点的特征数据。(矩阵)
    labels -- 节点的真实标签。
    mask -- 用于指定哪些节点参与训练的掩码数组。
    loss_fcn -- 损失函数,用于计算预测结果与真实标签之间的差异。
    optimizer -- 优化器,用于更新模型参数以减小损失。

    返回:
    loss -- 训练过程中的损失值。
    """
    # 将模型设置为训练模式
    model.train()
    # 通过模型预测获得logits。logits是一个向量，表示每个节点的预测概率。
    logits = model(g, features)
    # 计算参与训练的节点的损失
    loss = loss_fcn(logits[mask], labels[mask])
    # 清空梯度缓存
    optimizer.zero_grad()
    # 反向传播计算梯度
    loss.backward()
    # 更新模型参数
    optimizer.step()
    # 返回损失值
    return loss.item()

def test(g, model, features, labels, mask):
    """
    测试模型的性能。

    使用给定的图结构数据、特征、标签和掩码,评估模型的损失和准确性。

    参数:
    - g: 图数据,用于模型输入。
    - model: 待测试的模型。
    - features: 图节点的特征矩阵。
    - labels: 图节点的标签矩阵。
    - mask: 用于标识哪些节点用于评估的掩码矩阵。

    返回:
    - loss: 模型在测试数据上的损失。
    - accuracy: 模型在测试数据上的准确率。
    """
    # 将模型设置为评估模式
    model.eval()
    # 在评估过程中不计算梯度
    with torch.no_grad():
        # 通过模型预测得到logits
        # 假设模型输出未经过softmax处理,如果是经过softmax的输出,则不需要使用F.log_softmax
        logits = model(g, features)
        # 计算掩码下标签的交叉熵损失
        # 计算交叉熵损失,nn.CrossEntropyLoss内部包含了log_softmax和NLLLoss,因此直接传入logits和labels
        # F.cross_entropy函数在内部会对输入的logits应用softmax函数，然后再计算交叉熵损失。
        # 因此，当调用F.cross_entropy(logits[mask], labels[mask])时，它会先应用softmax把logits转化为概率分布，
        # 然后再计算这个概率分布与真实标签之间的交叉熵损失。
        loss = F.cross_entropy(logits[mask], labels[mask])
        # 预测值的获取（返回每行的索引值，即将二维张量logits每行中的最大值的索引位置找出来。
        # 其中，参数dim=1表示在第1维上进行操作。
        predictions = logits.argmax(dim=1)
        # 计算掩码下预测准确率，将布尔型数据转换为浮点型(True转换为1.0，False转换为0.0)，
        # 然后计算这些浮点数的平均值，以此来得到准确率。
        accuracy = (predictions[mask] == labels[mask]).float().mean()
    # 返回损失和准确率
    # 使用.item()方法将PyTorch张量转换为Python标量
    return loss.item(), accuracy.item()  # 返回交叉熵损失和准确率

def main():
    """
    主函数,用于执行图卷积网络(GCN)的训练和测试。
    """
    # 定义模型 hyperparameters
    # in_feats表示输入特征的维度，这里是1433。
    # hidden_size表示隐藏层的神经元数量，这里是16。
    # num_classes表示分类的类别数量，这里是7。
    # learning_rate表示学习率，用于控制模型在训练过程中的参数更新速度，这里是0.01。
    # epochs表示训练的轮数，即模型对整个训练集进行迭代的次数，这里是200。
    # step表示打印训练日志的频率，即每隔多少轮打印一次训练信息。
    in_feats = 1433  # Cora特征维度
    hidden_size = 16
    num_classes = 7
    learning_rate = 0.01
    epochs = 150
    step = 10

    # 加载Cora数据集的图结构、特征和标签
    g, features, labels, train_mask = load_cora_graph_data()

    # 初始化模型、损失函数和优化器
    model = GCN(in_feats, hidden_size, num_classes)
    loss_fcn = nn.CrossEntropyLoss()
    optimizer = optim.Adam(model.parameters(), lr=learning_rate)

    # 初始化用于记录训练损失和测试准确率的列表
    losses = []
    accuracies = []

    # 训练模型
    for epoch in range(epochs):
        loss = train(g, model, features, labels, train_mask, loss_fcn, optimizer)
        losses.append(loss)
        # 每隔step个epoch,测试模型并记录准确率
        if epoch % step == 0 :
            _, acc = test(g, model, features, labels, train_mask)
            accuracies.append(acc)
            print(f"Epoch {epoch+1}, Loss: {loss:.4f}, Test Accuracy: {acc:.8f}")

    # 绘制训练损失和测试准确率的变化曲线
    plt.figure(figsize=(12, 6))
    plt.subplot(1, 2, 1)
    plt.plot(losses, label='Training Loss')
    plt.xlabel('Epochs')
    plt.ylabel('Loss')
    plt.legend()

    plt.subplot(1, 2, 2)
    plt.plot(accuracies, label='Test Accuracy', color='orange')
    plt.xlabel(f'Epochs (every {step}th)')
    plt.ylabel('Accuracy')
    plt.legend()

    plt.show()

    # 测试训练好的模型并打印测试损失和准确率
    loss, acc = test(g, model, features, labels, train_mask)
    print(f"Test Loss: {loss:.4f}, Test Accuracy: {acc:.8f}")

if __name__ == "__main__":
    main()
