import pandas as pd

df = pd.read_csv("evaluation_sample.csv")

total = len(df)
correct = df["IsCorrect"].sum()

precision = correct / total

print(f"✅ 评估样本数: {total}")
print(f"✅ 正确三元组数: {correct}")
print(f"🎯 抽取准确率 (Precision): {precision:.2%}")
