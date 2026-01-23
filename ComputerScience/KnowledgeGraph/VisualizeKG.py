import pandas as pd
import networkx as nx
from pyvis.network import Network

# ==============================
# Step 1: 读取三元组
# ==============================
triples_df = pd.read_csv("triples.csv")
print(f"✅ 加载 {len(triples_df)} 条三元组")

# ==============================
# Step 2: 构建 NetworkX 有向图
# ==============================
G = nx.DiGraph()

for _, row in triples_df.iterrows():
    s = str(row["Subject"]).strip()
    p = str(row["Predicate"]).strip()
    o = str(row["Object"]).strip()
    if s and p and o:
        G.add_edge(s, o, label=p)

print(f"✅ 图谱包含 {G.number_of_nodes()} 个节点，{G.number_of_edges()} 条边")

# ==============================
# Step 3: 节点分类与着色（可选但推荐）
# ==============================
def classify_node(node):
    node_lower = node.lower()
    if "手枪" in node or "冲锋枪" in node or "散弹枪" in node or "突击步枪" in node or "卡宾枪" in node or "狙击步枪" in node or "步枪" in node or "左轮" in node:
        return "weapon"
    elif node in ["美国", "俄罗斯", "德国", "意大利", "法国", "以色列", "英国", "中国", "日本", "捷克", "南非", "比利时", "奥地利", "前苏联", "乌克兰", "瑞士", "芬兰", "巴西", "丹麦", "西班牙", "挪威", "荷兰", "葡萄牙", "加拿大", "希腊", "澳大利亚", "新西兰", "埃及", "罗马尼亚", "塞尔维亚", "突尼斯", "印度", "匈牙利", "瑞典", "土耳其", "波兰", "韩国"]:
        return "country"
    elif "公司" in node or "企业" in node or "厂" in node or "设计局" in node or "研究所" in node or "兵工厂" in node or "KBP" in node or "FNH" in node or "H&K" in node or "SIG" in node or "I.M.I" in node or "zVI" in node or "Armsel" in node or "EDM" in node or "雷明顿" in node or "柯尔特" in node or "伯莱塔" in node:
        return "company"
    elif "mm" in node or "rds" in node or "kg" in node or "m/s" in node or "R/min" in node or node.replace('.', '').replace('×', '').isdigit() or any(c.isdigit() for c in node):
        return "value"
    elif node in ["全枪长", "枪管长", "全枪重", "空枪重", "弹匣容量", "口径", "子弹规格", "初速", "枪口动能", "理论射速", "有效射程", "膛线", "瞄准基线长", "射速", "表尺射程", "分解后最大长度", "缠距", "战斗全重", "枪口初速"]:
        return "attribute"
    else:
        return "other"

# 设置节点颜色
color_map = {
    "weapon": "#FF6F61",     # 红橙 - 武器
    "country": "#4ECDC4",    # 青蓝 - 国家
    "company": "#FFE66D",    # 黄色 - 公司
    "attribute": "#A0D2EB",  # 浅蓝 - 属性名
    "value": "#C9C9C9",      # 灰色 - 属性值
    "other": "#B5B5B5"       # 默认灰
}

# ==============================
# Step 4: 使用 PyVis 生成交互式图谱
# ==============================
net = Network(
    height="900px",
    width="100%",
    bgcolor="#ffffff",
    font_color="black",
    directed=True
)
net.set_options("""
var options = {
  "physics": {
    "enabled": true,
    "stabilization": {"iterations": 100},
    "barnesHut": {
      "gravitationalConstant": -8000,
      "centralGravity": 0.1,
      "springLength": 100,
      "springConstant": 0.01,
      "damping": 0.09
    }
  },
  "edges": {
    "arrows": {"to": {"enabled": true}},
    "smooth": {"type": "dynamic"},
    "font": {"size": 12, "align": "horizontal"}
  },
  "nodes": {
    "font": {"size": 14, "face": "Arial"},
    "scaling": {"label": {"enabled": false}}
  }
}
""")

# 添加节点（带颜色）
for node in G.nodes():
    category = classify_node(node)
    color = color_map.get(category, "#B5B5B5")
    # 缩短过长的标签（可选）
    label = node if len(node) <= 30 else node[:27] + "..."
    net.add_node(node, label=label, color=color, title=node)

# 添加边（带关系标签）
for s, o, data in G.edges(data=True):
    net.add_edge(s, o, label=data.get("label", ""), title=data.get("label", ""))

# ==============================
# Step 5: 保存并打开 HTML
# ==============================
output_file = "knowledge_graph.html"
net.save_graph(output_file)
print(f"✅ 知识图谱已保存为 {output_file}")
print("💡 用浏览器打开该文件即可交互式查看图谱！")
