import numpy as np
import pandas as pd
from sklearn import svm
from sklearn.model_selection import train_test_split, KFold, LeaveOneOut, cross_val_score
from sklearn.metrics import accuracy_score
import matplotlib.pyplot as plt

# 1. 数据加载与预处理
print("--- 1. Loading and Preprocessing Data ---")
data = pd.read_csv("src/classification.csv")  # 读取csv
data.dropna(inplace=True)  # 删除有缺失的样本
print(f"Dataset shape after dropping NaNs: {data.shape}")

y = data['class']  # 标签
X = data.drop(['class'], axis=1)  # 去掉标签的数据子集
X_values = X.values  # 转换为 numpy array
y_values = y.values  # 转换为 numpy array
print(f"Features shape: {X_values.shape}, Labels shape: {y_values.shape}")
print(f"Unique classes: {np.unique(y_values)}")

# 2. SVM 模型与参数设置
print("\n--- 2. Setting up SVM Model for C Parameter Sweep ---")

# --- 固定的 SVM 参数 ---
# 为了专注于 C 值的影响，其他参数保持不变
kernel_type = 'rbf'  # 核函数类型: 'linear', 'poly', 'rbf', 'sigmoid'
decision_shape = 'ovr'  # 多分类策略: 'ovr', 'ovo'
print(f"Using SVM with fixed parameters: kernel='{kernel_type}', decision_function_shape='{decision_shape}'")

# --- C 值范围 ---
C_values = np.linspace(0.1, 1.0, 10)
print(f"Testing C values: {C_values}")

# 存储每种方法在不同 C 值下的准确率
accuracies_ho = []
accuracies_kf = []
accuracies_loo = []

# 3. 遍历 C 值并应用三种数据划分方法
print("\n--- 3. Sweeping C Values and Applying Data Splitting Methods ---")
for C_val in C_values:
    print(f"Testing C = {C_val:.2f}")
    
    # 创建 SVM 分类器实例 (使用当前 C 值)
    svm_model = svm.SVC(C=C_val, kernel=kernel_type, decision_function_shape=decision_shape)

    # 3.1 数据划分方法 - 留出法 (Hold-out)
    X_train_ho, X_test_ho, y_train_ho, y_test_ho = train_test_split(
        X_values, y_values, test_size=0.2, random_state=42, stratify=y_values
    )
    svm_model_ho = svm.SVC(C=C_val, kernel=kernel_type, decision_function_shape=decision_shape)
    svm_model_ho.fit(X_train_ho, y_train_ho)
    y_pred_ho = svm_model_ho.predict(X_test_ho)
    accuracy_ho = accuracy_score(y_test_ho, y_pred_ho)
    accuracies_ho.append(accuracy_ho)

    # 3.2 数据划分方法 - k折交叉验证 (K-Fold Cross-Validation)
    k_folds = 10
    kf = KFold(n_splits=k_folds, shuffle=True, random_state=42)
    cv_scores_svm = cross_val_score(svm_model, X_values, y_values, cv=kf, scoring='accuracy')
    mean_cv_accuracy = cv_scores_svm.mean()
    accuracies_kf.append(mean_cv_accuracy)

    # 3.3 数据划分方法 - 留一法 (Leave-One-Out)
    loo = LeaveOneOut()
    loo_scores = []
    for train_index, test_index in loo.split(X_values):
        X_train_loo, X_test_loo = X_values[train_index], X_values[test_index]
        y_train_loo, y_test_loo = y_values[train_index], y_values[test_index]
        svm_model_loo = svm.SVC(C=C_val, kernel=kernel_type, decision_function_shape=decision_shape)
        svm_model_loo.fit(X_train_loo, y_train_loo)
        y_pred_loo = svm_model_loo.predict(X_test_loo)
        sample_acc = accuracy_score(y_test_loo, y_pred_loo)
        loo_scores.append(sample_acc)
    loo_accuracy = np.mean(loo_scores)
    accuracies_loo.append(loo_accuracy)

# 4. 结果可视化 - 绘制 C 值对准确率影响的图表
print("\n--- 4. Visualization - C Parameter Impact ---")

# 4.1 留出法准确率图
plt.figure(figsize=(8, 5))
plt.plot(C_values, accuracies_ho, marker='o', linestyle='-', color='b', label='Hold-out Accuracy')
plt.xlabel('C Parameter')
plt.ylabel('Accuracy')
plt.title(f'SVM Accuracy vs C Value (Hold-out Method)\nKernel={kernel_type}, Decision Shape={decision_shape}')
plt.grid(True, alpha=0.3)
plt.legend()
plt.tight_layout()
plt.savefig('image/svm_c_impact_ho.jpg')
plt.show()

# 4.2 K 折交叉验证准确率图
plt.figure(figsize=(8, 5))
plt.plot(C_values, accuracies_kf, marker='s', linestyle='-', color='g', label=f'{k_folds}-Fold CV Accuracy')
plt.xlabel('C Parameter')
plt.ylabel('Accuracy')
plt.title(f'SVM Accuracy vs C Value (K-Fold Cross-Validation)\nKernel={kernel_type}, Decision Shape={decision_shape}')
plt.grid(True, alpha=0.3)
plt.legend()
plt.tight_layout()
plt.savefig('image/svm_c_impact_kf.jpg')
plt.show()

# 4.3 留一法准确率图
plt.figure(figsize=(8, 5))
plt.plot(C_values, accuracies_loo, marker='^', linestyle='-', color='r', label='Leave-One-Out Accuracy')
plt.xlabel('C Parameter')
plt.ylabel('Accuracy')
plt.title(f'SVM Accuracy vs C Value (Leave-One-Out Method)\nKernel={kernel_type}, Decision Shape={decision_shape}')
plt.grid(True, alpha=0.3)
plt.legend()
plt.tight_layout()
plt.savefig('image/svm_c_impact_loo.jpg')
plt.show()

# 5. 结果总结
print("\n--- 5. Summary ---")
print(f"Dataset: classification.csv ({X_values.shape[0]} samples, {X_values.shape[1]} features, {len(np.unique(y_values))} classes)")
print(f"SVM Parameters: kernel='{kernel_type}', decision_function_shape='{decision_shape}'")
print(f"Tested C values: {C_values}")
print(f"Hold-out accuracies: {accuracies_ho}")
print(f"K-Fold CV accuracies: {accuracies_kf}")
print(f"Leave-One-Out accuracies: {accuracies_loo}")

# 找到每种方法下的最佳 C 值和准确率
best_c_ho = C_values[np.argmax(accuracies_ho)]
best_acc_ho = max(accuracies_ho)
best_c_kf = C_values[np.argmax(accuracies_kf)]
best_acc_kf = max(accuracies_kf)
best_c_loo = C_values[np.argmax(accuracies_loo)]
best_acc_loo = max(accuracies_loo)

print(f"\nBest C (Hold-out): {best_c_ho:.2f}, Accuracy: {best_acc_ho:.4f}")
print(f"Best C (K-Fold CV): {best_c_kf:.2f}, Accuracy: {best_acc_kf:.4f}")
print(f"Best C (Leave-One-Out): {best_c_loo:.2f}, Accuracy: {best_acc_loo:.4f}")