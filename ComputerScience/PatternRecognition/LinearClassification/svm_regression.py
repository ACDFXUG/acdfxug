import numpy as np
import pandas as pd
from sklearn import svm
from sklearn.model_selection import train_test_split, KFold, LeaveOneOut
from sklearn.metrics import mean_squared_error, r2_score
import matplotlib.pyplot as plt

# 1. 数据加载与预处理
print("--- 1. Loading and Preprocessing Data (Regression) ---")
data = pd.read_csv("src/regression.csv")  # 读取csv
# 注意：regression.csv 中包含 'NA' 值，需要先处理
data.dropna(inplace=True)  # 删除有缺失的样本
print(f"Dataset shape after dropping NaNs: {data.shape}")

# 回归任务：最后一列是目标值 (target)
y = data.iloc[:, -1]  # 标签 (最后一列)
X = data.iloc[:, :-1]  # 去掉标签的数据子集 (所有其他列)
X_values = X.values  # 转换为 numpy array
y_values = y.values  # 转换为 numpy array
print(f"Features shape: {X_values.shape}, Target shape: {y_values.shape}")

# 2. SVM 回归模型与参数设置
print("\n--- 2. Setting up SVM Regression Model for C Parameter Sweep ---")

# --- 固定的 SVM 参数 (for C sweep) ---
# 为了专注于 C 值的影响，其他参数保持不变
kernel_type = 'linear'  # 核函数类型: 'linear', 'poly', 'rbf', 'sigmoid'
epsilon_value = 0.1 # epsilon-tube parameter
print(f"Using SVM Regressor with fixed parameters: kernel='{kernel_type}', epsilon={epsilon_value}")

# --- C 值范围 ---
C_values = np.linspace(0.1, 1.0, 10)
print(f"Testing C values: {C_values}")

# 存储每种方法在不同 C 值下的 MSE
mse_ho = []
mse_kf = []
mse_loo = []

# 3. 遍历 C 值并应用三种数据划分方法
print("\n--- 3. Sweeping C Values and Applying Data Splitting Methods ---")
for C_val in C_values:
    print(f"Testing C = {C_val:.2f}")
    
    # 创建 SVM 回归器实例 (使用当前 C 值)
    svm_model = svm.SVR(C=C_val, kernel=kernel_type, epsilon=epsilon_value)

    # 3.1 数据划分方法 - 留出法 (Hold-out)
    X_train_ho, X_test_ho, y_train_ho, y_test_ho = train_test_split(
        X_values, y_values, test_size=0.2, random_state=42
    )
    svm_model_ho = svm.SVR(C=C_val, kernel=kernel_type, epsilon=epsilon_value)
    svm_model_ho.fit(X_train_ho, y_train_ho)
    y_pred_ho = svm_model_ho.predict(X_test_ho)
    current_mse_ho = mean_squared_error(y_test_ho, y_pred_ho)
    mse_ho.append(current_mse_ho)

    # 3.2 数据划分方法 - k折交叉验证 (K-Fold Cross-Validation)
    k_folds = 10
    kf = KFold(n_splits=k_folds, shuffle=True, random_state=42)
    cv_scores_mse = []
    for train_index, test_index in kf.split(X_values):
        X_train_kf, X_test_kf = X_values[train_index], X_values[test_index]
        y_train_kf, y_test_kf = y_values[train_index], y_values[test_index]
        svm_model_kf = svm.SVR(C=C_val, kernel=kernel_type, epsilon=epsilon_value)
        svm_model_kf.fit(X_train_kf, y_train_kf)
        y_pred_kf = svm_model_kf.predict(X_test_kf)
        cv_scores_mse.append(mean_squared_error(y_test_kf, y_pred_kf))
    mean_cv_mse = np.mean(cv_scores_mse)
    mse_kf.append(mean_cv_mse)

    # 3.3 数据划分方法 - 留一法 (Leave-One-Out)
    loo = LeaveOneOut()
    loo_scores_mse = []
    for train_index, test_index in loo.split(X_values):
        X_train_loo, X_test_loo = X_values[train_index], X_values[test_index]
        y_train_loo, y_test_loo = y_values[train_index], y_values[test_index]
        svm_model_loo = svm.SVR(C=C_val, kernel=kernel_type, epsilon=epsilon_value)
        svm_model_loo.fit(X_train_loo, y_train_loo)
        y_pred_loo = svm_model_loo.predict(X_test_loo)
        sample_mse = mean_squared_error(y_test_loo, y_pred_loo)
        loo_scores_mse.append(sample_mse)
    mean_loo_mse = np.mean(loo_scores_mse)
    mse_loo.append(mean_loo_mse)

# 4. 结果可视化 - 绘制 C 值对 MSE 影响的图表
print("\n--- 4. Visualization - C Parameter Impact on MSE ---")

# 4.1 留出法 MSE 图
plt.figure(figsize=(8, 5))
plt.plot(C_values, mse_ho, marker='o', linestyle='-', color='b', label='Hold-out MSE')
plt.xlabel('C Parameter')
plt.ylabel('Mean Squared Error (MSE)')
plt.title(f'SVM Regressor MSE vs C Value (Hold-out Method)\nKernel={kernel_type}, Epsilon={epsilon_value}')
plt.grid(True, alpha=0.3)
plt.legend()
plt.tight_layout()
plt.savefig('image/svm_regression_c_impact_mse_ho.jpg')
plt.show()

# 4.2 K 折交叉验证 MSE 图
plt.figure(figsize=(8, 5))
plt.plot(C_values, mse_kf, marker='s', linestyle='-', color='g', label=f'{k_folds}-Fold CV MSE')
plt.xlabel('C Parameter')
plt.ylabel('Mean Squared Error (MSE)')
plt.title(f'SVM Regressor MSE vs C Value (K-Fold Cross-Validation)\nKernel={kernel_type}, Epsilon={epsilon_value}')
plt.grid(True, alpha=0.3)
plt.legend()
plt.tight_layout()
plt.savefig('image/svm_regression_c_impact_mse_kf.jpg')
plt.show()

# 4.3 留一法 MSE 图
plt.figure(figsize=(8, 5))
plt.plot(C_values, mse_loo, marker='^', linestyle='-', color='r', label='Leave-One-Out MSE')
plt.xlabel('C Parameter')
plt.ylabel('Mean Squared Error (MSE)')
plt.title(f'SVM Regressor MSE vs C Value (Leave-One-Out Method)\nKernel={kernel_type}, Epsilon={epsilon_value}')
plt.grid(True, alpha=0.3)
plt.legend()
plt.tight_layout()
plt.savefig('image/svm_regression_c_impact_mse_loo.jpg')
plt.show()

# 5. 绘制 C=1.0 时的真实值 vs 预测值散点图
print("\n--- 5. Visualization - True vs Predicted Values (C=1.0) ---")
C_for_plot = 1.0
print(f"Generating True vs Predicted plots for C = {C_for_plot}")

# 5.1 Hold-out True vs Predicted (C=1.0)
# Re-run Hold-out with C=1.0 to get predictions for the plot
X_train_ho_plot, X_test_ho_plot, y_train_ho_plot, y_test_ho_plot = train_test_split(
    X_values, y_values, test_size=0.2, random_state=42
)
svm_model_ho_plot = svm.SVR(C=C_for_plot, kernel=kernel_type, epsilon=epsilon_value)
svm_model_ho_plot.fit(X_train_ho_plot, y_train_ho_plot)
y_pred_ho_plot = svm_model_ho_plot.predict(X_test_ho_plot)
mse_ho_plot = mean_squared_error(y_test_ho_plot, y_pred_ho_plot)
r2_ho_plot = r2_score(y_test_ho_plot, y_pred_ho_plot)

plt.figure(figsize=(8, 6))
plt.scatter(y_test_ho_plot, y_pred_ho_plot, alpha=0.6, color='blue')
plt.plot([y_test_ho_plot.min(), y_test_ho_plot.max()], [y_test_ho_plot.min(), y_test_ho_plot.max()], 'r--', lw=2, label='Perfect Prediction')
plt.xlabel('True Values')
plt.ylabel('Predicted Values')
plt.title(f'SVM Regressor: Predicted vs True Values (Hold-out, C={C_for_plot})\nMSE: {mse_ho_plot:.4f}, R2: {r2_ho_plot:.4f}')
plt.legend()
plt.grid(True, alpha=0.3)
plt.tight_layout()
plt.savefig('image/svm_regression_ho_scatter_c1.jpg')
plt.show()

# 5.2 K-Fold CV True vs Predicted (C=1.0)
# Re-run K-Fold CV with C=1.0 to get all predictions for the plot
k_folds_plot = 10
kf_plot = KFold(n_splits=k_folds_plot, shuffle=True, random_state=42)
all_y_true_kf_plot = []
all_y_pred_kf_plot = []
for train_index, test_index in kf_plot.split(X_values):
    X_train_kf_plot, X_test_kf_plot = X_values[train_index], X_values[test_index]
    y_train_kf_plot, y_test_kf_plot = y_values[train_index], y_values[test_index]
    svm_model_kf_plot = svm.SVR(C=C_for_plot, kernel=kernel_type, epsilon=epsilon_value)
    svm_model_kf_plot.fit(X_train_kf_plot, y_train_kf_plot)
    y_pred_kf_plot = svm_model_kf_plot.predict(X_test_kf_plot)
    all_y_true_kf_plot.extend(y_test_kf_plot)
    all_y_pred_kf_plot.extend(y_pred_kf_plot)

all_y_true_kf_plot = np.array(all_y_true_kf_plot)
all_y_pred_kf_plot = np.array(all_y_pred_kf_plot)
mse_kf_plot = mean_squared_error(all_y_true_kf_plot, all_y_pred_kf_plot)
r2_kf_plot = r2_score(all_y_true_kf_plot, all_y_pred_kf_plot)

plt.figure(figsize=(8, 6))
plt.scatter(all_y_true_kf_plot, all_y_pred_kf_plot, alpha=0.6, color='green')
plt.plot([all_y_true_kf_plot.min(), all_y_true_kf_plot.max()], [all_y_true_kf_plot.min(), all_y_true_kf_plot.max()], 'r--', lw=2, label='Perfect Prediction')
plt.xlabel('True Values')
plt.ylabel('Predicted Values')
plt.title(f'SVM Regressor: Predicted vs True Values (K-Fold CV, C={C_for_plot})\nMSE: {mse_kf_plot:.4f}, R2: {r2_kf_plot:.4f}')
plt.legend()
plt.grid(True, alpha=0.3)
plt.tight_layout()
plt.savefig('image/svm_regression_kf_scatter_c1.jpg')
plt.show()

# 5.3 Leave-One-Out True vs Predicted (C=1.0)
# Re-run LOO with C=1.0 to get all predictions for the plot
loo_plot = LeaveOneOut()
all_y_true_loo_plot = []
all_y_pred_loo_plot = []
for train_index, test_index in loo_plot.split(X_values):
    X_train_loo_plot, X_test_loo_plot = X_values[train_index], X_values[test_index]
    y_train_loo_plot, y_test_loo_plot = y_values[train_index], y_values[test_index]
    svm_model_loo_plot = svm.SVR(C=C_for_plot, kernel=kernel_type, epsilon=epsilon_value)
    svm_model_loo_plot.fit(X_train_loo_plot, y_train_loo_plot)
    y_pred_loo_plot = svm_model_loo_plot.predict(X_test_loo_plot)
    all_y_true_loo_plot.extend(y_test_loo_plot)
    all_y_pred_loo_plot.extend(y_pred_loo_plot)

all_y_true_loo_plot = np.array(all_y_true_loo_plot)
all_y_pred_loo_plot = np.array(all_y_pred_loo_plot)
mse_loo_plot = mean_squared_error(all_y_true_loo_plot, all_y_pred_loo_plot)
r2_loo_plot = r2_score(all_y_true_loo_plot, all_y_pred_loo_plot)

plt.figure(figsize=(8, 6))
plt.scatter(all_y_true_loo_plot, all_y_pred_loo_plot, alpha=0.6, color='orange')
plt.plot([all_y_true_loo_plot.min(), all_y_true_loo_plot.max()], [all_y_true_loo_plot.min(), all_y_true_loo_plot.max()], 'r--', lw=2, label='Perfect Prediction')
plt.xlabel('True Values')
plt.ylabel('Predicted Values')
plt.title(f'SVM Regressor: Predicted vs True Values (Leave-One-Out, C={C_for_plot})\nMSE: {mse_loo_plot:.4f}, R2: {r2_loo_plot:.4f}')
plt.legend()
plt.grid(True, alpha=0.3)
plt.tight_layout()
plt.savefig('image/svm_regression_loo_scatter_c1.jpg')
plt.show()


# 6. 结果总结
print("\n--- 6. Summary ---")
print(f"Dataset: regression.csv ({X_values.shape[0]} samples, {X_values.shape[1]} features)")
print(f"SVM Regressor Parameters: kernel='{kernel_type}', epsilon={epsilon_value}'")
print(f"Tested C values: {C_values}")
print(f"Hold-out MSEs: {mse_ho}")
print(f"K-Fold CV MSEs: {mse_kf}")
print(f"Leave-One-Out MSEs: {mse_loo}")

# 找到每种方法下的最佳 C 值和 MSE
best_c_ho_mse = C_values[np.argmin(mse_ho)]
best_mse_ho = min(mse_ho)
best_c_kf_mse = C_values[np.argmin(mse_kf)]
best_mse_kf = min(mse_kf)
best_c_loo_mse = C_values[np.argmin(mse_loo)]
best_mse_loo = min(mse_loo)

print(f"\nBest C (Hold-out, by MSE): {best_c_ho_mse:.2f}, MSE: {best_mse_ho:.4f}")
print(f"Best C (K-Fold CV, by MSE): {best_c_kf_mse:.2f}, MSE: {best_mse_kf:.4f}")
print(f"Best C (Leave-One-Out, by MSE): {best_c_loo_mse:.2f}, MSE: {best_mse_loo:.4f}")

print(f"\nC=1.0 Results (True vs Predicted Plots):")
print(f"  Hold-out MSE (C=1.0): {mse_ho_plot:.4f}, R2: {r2_ho_plot:.4f}")
print(f"  K-Fold CV MSE (C=1.0): {mse_kf_plot:.4f}, R2: {r2_kf_plot:.4f}")
print(f"  Leave-One-Out MSE (C=1.0): {mse_loo_plot:.4f}, R2: {r2_loo_plot:.4f}")