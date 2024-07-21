import networkx as nx
import matplotlib.pyplot as plt

# 创建布尔格的哈斯图
def create_boole_graph():
    nodes = [bin(i)[2:].zfill(4) for i in range(16)]  # 创建所有二进制节点
    G = nx.DiGraph()  # 创建有向图
    for node in nodes:
        for other_node in nodes:
            if node <= other_node:  # 如果包含关系成立，添加边
                G.add_edge(node, other_node)
    return G

# 绘制哈斯图
def draw_boole_graph(G):
    pos = nx.kamada_kawai_layout(G)  # 使用Kamada-Kawai布局
    nx.draw_networkx_nodes(G, pos, node_color='lightblue', node_size=700)
    nx.draw_networkx_edges(G, pos, edge_color='gray', arrows=True)
    nx.draw_networkx_labels(G, pos, font_size=16, font_family='sans-serif')
    plt.axis('off')  # 不显示坐标轴
    plt.show()

# 主程序
G = create_boole_graph()
draw_boole_graph(G)