import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split, KFold, LeaveOneOut
from sklearn.metrics import accuracy_score, classification_report, confusion_matrix
import matplotlib.pyplot as plt
import seaborn as sns

# --- 手动实现 Fisher LDA ---
class FisherLDA:
    """
    手动实现的 Fisher LDA 分类器。
    """
    def __init__(self):
        self.classes = None
        self.classifiers = {} # 存储每个二分类器 (class vs rest)

    def fit(self, X, y):
        self.classes = np.unique(y)
        self.mean_vectors = {}
        self.S_W = np.zeros((X.shape[1], X.shape[1])) # Within-class scatter matrix

        # 计算每个类的均值向量
        for c in self.classes:
            class_samples = X[y == c]
            self.mean_vectors[c] = np.mean(class_samples, axis=0)

        # 计算 Within-class scatter matrix S_W
        for c in self.classes:
            class_samples = X[y == c]
            mean_vec = self.mean_vectors[c].reshape(-1, 1)
            S_c = np.zeros((X.shape[1], X.shape[1]))
            for row in class_samples:
                row, mean_vec = row.reshape(-1, 1), mean_vec.reshape(-1, 1)
                S_c += (row - mean_vec).dot((row - mean_vec).T)
            self.S_W += S_c

        # 计算每个二分类器 (class vs rest)
        for c in self.classes:
            y_binary = (y == c).astype(int) # 1 if class c, 0 otherwise (Rest)
            # 计算二分类的均值
            mean_c = self.mean_vectors[c].reshape(-1, 1)
            # 计算 Rest 的均值 (所有非 c 类样本的均值)
            rest_samples = X[y != c]
            mean_rest = np.mean(rest_samples, axis=0).reshape(-1, 1)

            # 计算 Between-class scatter matrix S_B for this binary problem
            n_c = np.sum(y_binary == 1)
            n_rest = np.sum(y_binary == 0)
            total_samples = X.shape[0]
            # 简化计算投影方向 w
            # w is proportional to S_W^{-1} * (mean_c - mean_rest)
            # For projection, we only need the direction
            S_W_inv = np.linalg.pinv(self.S_W) # Use pseudo-inverse for robustness
            w = S_W_inv.dot((mean_c - mean_rest))
            w = w / np.linalg.norm(w) # Normalize w

            # Store the projection vector and a threshold
            # Threshold can be calculated based on the projected means
            proj_mean_c = w.T.dot(mean_c)[0]
            proj_mean_rest = w.T.dot(mean_rest)[0]
            # A simple threshold is the midpoint between projected means
            threshold = (proj_mean_c + proj_mean_rest) / 2.0

            self.classifiers[c] = {'w': w.flatten(), 'threshold': threshold}

    def predict(self, X):
        if self.classes is None or not self.classifiers:
            raise ValueError("Model has not been fitted yet.")
        
        n_samples = X.shape[0]
        scores = np.zeros((n_samples, len(self.classes)))

        for i, c in enumerate(self.classes):
            w = self.classifiers[c]['w'].reshape(-1, 1)
            threshold = self.classifiers[c]['threshold']
            # Project samples onto the line
            proj_X = X.dot(w)
            # Score could be distance from threshold or just the projection value
            # For OvR, higher projection value towards class c's mean indicates higher likelihood
            scores[:, i] = proj_X.flatten() - threshold # Positive if likely class c

        # Predict the class with the highest score
        predictions = self.classes[np.argmax(scores, axis=1)]
        return predictions

# 1. 数据加载与预处理
print("--- 1. Loading and Preprocessing Data ---")
data = pd.read_csv("src/classification.csv") # 读取csv
print(f"Dataset shape: {data.shape}")

# 检查是否有缺失值
print(f"Missing values:\n{data.isnull().sum()}")

y = data['class'] # 标签
X = data.drop(['class'], axis=1) # 去掉标签的数据子集
X_values = X.values # 转换为 numpy array
y_values = y.values # 转换为 numpy array
print(f"Features shape: {X_values.shape}, Labels shape: {y_values.shape}")
print(f"Unique classes: {np.unique(y_values)}")

# 2. Fisher线性判别分析 (LDA) 模型
print("\n--- 2. Applying Manually Implemented Fisher's Linear Discriminant Analysis (LDA) ---")
fisher_model = FisherLDA()

# --- Helper Functions for Visualization ---
def plot_confusion_matrix(y_true, y_pred, title="Confusion Matrix", filename="confusion_matrix.jpg"):
    """绘制混淆矩阵热图"""
    cm = confusion_matrix(y_true, y_pred)
    plt.figure(figsize=(6, 4))
    sns.heatmap(cm, annot=True, fmt='d', cmap='Blues', xticklabels=np.unique(y_values), yticklabels=np.unique(y_values))
    plt.title(title)
    plt.ylabel('True Label')
    plt.xlabel('Predicted Label')
    plt.tight_layout()
    plt.savefig(f'image/{filename}')
    plt.show()

def plot_density_projection_for_fold(X_train, y_train, X_test, y_test, fold_num, title_suffix="", filename_suffix=""):
    """
    为单个 fold 绘制密度投影图。
    这个函数会为给定的训练集训练一个 LDA 模型，并用它来投影测试集。
    """
    # 创建并训练该 fold 的 LDA 模型
    model_fold = FisherLDA()
    model_fold.fit(X_train, y_train)
    
    # 使用该模型的投影方向进行投影
    # 选择一个代表性的方向，例如 Class 1 vs Rest
    representative_w = model_fold.classifiers[model_fold.classes[0]]['w']
    X_proj = X_test.dot(representative_w)
    
    plt.figure(figsize=(8, 5))
    for class_label in np.unique(y_test):
        idx = y_test == class_label
        plt.hist(X_proj[idx], bins=20, alpha=0.7, label=f'Class {class_label}', density=True)
    plt.xlabel('Projection Value (LDA Direction from Fold)')
    plt.ylabel('Density')
    plt.title(f'Density Plot of LDA Projection (Fold {fold_num}) {title_suffix}')
    plt.legend()
    plt.grid(True, alpha=0.3)
    plt.tight_layout()
    plt.savefig(f'image/fisher_lda_density_fold_{fold_num}{filename_suffix}.jpg')
    plt.show()

def plot_all_folds_densities_in_one_plot(fold_models, X_test_list, y_test_list, method_name, normalize=False):
    """
    将所有 fold 的密度图绘制在同一个大图中。
    如果 normalize=True，则对每个 fold 的投影值进行标准化 (Z-score) 以便在同一坐标系中比较。
    """
    k = len(fold_models)
    colors = plt.cm.Set1(np.linspace(0, 1, k)) # 为每个 fold 生成不同的颜色
    
    plt.figure(figsize=(10, 6))
    
    for i in range(k):
        model_fold = fold_models[i]
        X_test = X_test_list[i]
        y_test = y_test_list[i]
        
        # 获取投影方向
        representative_w = model_fold.classifiers[model_fold.classes[0]]['w']
        X_proj = X_test.dot(representative_w)
        
        # 如果需要归一化
        if normalize:
            X_proj = (X_proj - np.mean(X_proj)) / np.std(X_proj)
        
        for class_label in np.unique(y_test):
            idx = y_test == class_label
            # 用不同颜色区分不同 fold
            plt.hist(X_proj[idx], bins=20, alpha=0.5, label=f'Fold {i+1}, Class {class_label}', 
                     color=colors[i], density=True)
    
    plt.xlabel('Projection Value' + (' (Normalized)' if normalize else ''))
    plt.ylabel('Density')
    plt.title(f'Fisher LDA Density Plots for All Folds ({method_name})')
    plt.legend(bbox_to_anchor=(1.05, 1), loc='upper left') # 放置图例在右侧，避免遮挡
    plt.grid(True, alpha=0.3)
    plt.tight_layout()
    plt.savefig(f'image/fisher_lda_all_folds_{method_name.replace(" ", "_").lower()}_combined.jpg')
    plt.show()

def plot_loo_summary(y_true, y_pred, title="Leave-One-Out Summary", filename="loo_summary.jpg"):
    """
    为留一法 (LOO) 生成一个综合性的“总图”，包含散点图、直方图和分类报告。
    """
    fig, axes = plt.subplots(2, 2, figsize=(14, 10))
    fig.suptitle(title, fontsize=16)
    
    # Subplot 1: Scatter plot of True vs Predicted
    ax1 = axes[0, 0]
    ax1.scatter(y_true, y_pred, c='blue', alpha=0.6, edgecolors='k')
    ax1.set_xlabel('True Label')
    ax1.set_ylabel('Predicted Label')
    ax1.set_title('True vs Predicted Labels (Scatter Plot)')
    ax1.grid(True, alpha=0.3)
    # Draw diagonal line for perfect prediction
    ax1.plot([min(y_true), max(y_true)], [min(y_true), max(y_true)], 'r--', lw=2)
    ax1.set_xticks(np.unique(y_true))
    ax1.set_yticks(np.unique(y_true))
    
    # Subplot 2: Histogram of True Labels
    ax2 = axes[0, 1]
    ax2.hist(y_true, bins=len(np.unique(y_true)), alpha=0.7, color='green', edgecolor='black')
    ax2.set_xlabel('Label')
    ax2.set_ylabel('Count')
    ax2.set_title('Distribution of True Labels')
    ax2.grid(True, alpha=0.3)
    ax2.set_xticks(np.unique(y_true))
    
    # Subplot 3: Histogram of Predicted Labels
    ax3 = axes[1, 0]
    ax3.hist(y_pred, bins=len(np.unique(y_true)), alpha=0.7, color='orange', edgecolor='black')
    ax3.set_xlabel('Label')
    ax3.set_ylabel('Count')
    ax3.set_title('Distribution of Predicted Labels')
    ax3.grid(True, alpha=0.3)
    ax3.set_xticks(np.unique(y_true))
    
    # Subplot 4: Classification Report as Text
    ax4 = axes[1, 1]
    report_str = classification_report(y_true, y_pred)
    ax4.text(0.01, 0.99, f'Classification Report:\n\n{report_str}', 
             verticalalignment='top', horizontalalignment='left',
             transform=ax4.transAxes, fontsize=10, family='monospace')
    ax4.axis('off')
    ax4.set_title('Detailed Classification Metrics')
    
    plt.tight_layout()
    plt.savefig(f'image/{filename}')
    plt.show()


# 2.1 数据划分方法 - 留出法 (Hold-out)
print("\n--- 2.1. Data Splitting Method - Hold-out ---")
X_train_ho, X_test_ho, y_train_ho, y_test_ho = train_test_split(
    X_values, y_values, test_size=0.2, random_state=42, stratify=y_values
)

fisher_model_ho = FisherLDA() # 为 Hold-out 创建新实例
fisher_model_ho.fit(X_train_ho, y_train_ho)
y_pred_ho = fisher_model_ho.predict(X_test_ho)

accuracy_ho = accuracy_score(y_test_ho, y_pred_ho)
print(f"Fisher LDA Accuracy (Hold-out, 80/20 split): {accuracy_ho:.4f}")
# Plot CM for Hold-out
plot_confusion_matrix(y_test_ho, y_pred_ho, "Fisher LDA Confusion Matrix (Hold-out)", "fisher_lda_confusion_ho.jpg")
# Plot Density for Hold-out (using its own model)
plot_density_projection_for_fold(X_train_ho, y_train_ho, X_test_ho, y_test_ho, 1, "(Hold-out)", "_ho")


# 2.2 数据划分方法 - k折交叉验证 (K-Fold Cross-Validation)
print("\n--- 2.2. Data Splitting Method - K-Fold Cross-Validation ---")
k_folds = 10
kf = KFold(n_splits=k_folds, shuffle=True, random_state=42)
cv_scores_fisher = []
all_y_true_kf = [] # Store all true labels for final CM
all_y_pred_kf = [] # Store all predictions for final CM

# Lists to store models and their corresponding test data for later plotting
fold_models_kf = []
X_test_list_kf = []
y_test_list_kf = []

for fold_idx, (train_index, test_index) in enumerate(kf.split(X_values)):
    X_train_kf, X_test_kf = X_values[train_index], X_values[test_index]
    y_train_kf, y_test_kf = y_values[train_index], y_values[test_index]
    
    fisher_model_kf = FisherLDA() # For each fold, create a new instance
    fisher_model_kf.fit(X_train_kf, y_train_kf)
    y_pred_kf = fisher_model_kf.predict(X_test_kf)
    
    fold_acc = accuracy_score(y_test_kf, y_pred_kf)
    cv_scores_fisher.append(fold_acc)
    
    all_y_true_kf.extend(y_test_kf)
    all_y_pred_kf.extend(y_pred_kf)
    
    # Store for later plotting
    fold_models_kf.append(fisher_model_kf)
    X_test_list_kf.append(X_test_kf)
    y_test_list_kf.append(y_test_kf)

print(f"Fisher LDA Cross-Validation Scores ({k_folds}-fold): {cv_scores_fisher}")
print(f"Fisher LDA Mean CV Accuracy: {np.mean(cv_scores_fisher):.4f} (+/- {np.std(cv_scores_fisher) * 2:.4f})")
# Plot CM for K-Fold (using all predictions from all folds)
plot_confusion_matrix(np.array(all_y_true_kf), np.array(all_y_pred_kf), f"Fisher LDA Confusion Matrix ({k_folds}-Fold CV)", "fisher_lda_confusion_kf.jpg")
# Plot individual density plots for each fold
for i in range(k_folds):
    plot_density_projection_for_fold(X_test_list_kf[i], y_test_list_kf[i], X_test_list_kf[i], y_test_list_kf[i], i+1, "(K-Fold CV)", "_kf")
# Plot all folds together in ONE single plot (with normalization)
plot_all_folds_densities_in_one_plot(fold_models_kf, X_test_list_kf, y_test_list_kf, "K-Fold CV", normalize=True)


# 2.3 数据划分方法 - 留一法 (Leave-One-Out)
print("\n--- 2.3. Data Splitting Method - Leave-One-Out (LOO) ---")
loo = LeaveOneOut()
loo_scores = []
all_y_true_loo = [] # Store all true labels for final CM
all_y_pred_loo = [] # Store all predictions for final CM

loo_model = FisherLDA() # Model instance for LOO loop

for fold_idx, (train_index, test_index) in enumerate(loo.split(X_values)):
    X_train_loo, X_test_loo = X_values[train_index], X_values[test_index]
    y_train_loo, y_test_loo = y_values[train_index], y_values[test_index]
    
    loo_model.fit(X_train_loo, y_train_loo)
    y_pred_loo = loo_model.predict(X_test_loo)
    
    # Accuracy for a single sample is 1 if correct, 0 if wrong
    sample_acc = accuracy_score(y_test_loo, y_pred_loo)
    loo_scores.append(sample_acc)
    
    all_y_true_loo.extend(y_test_loo)
    all_y_pred_loo.extend(y_pred_loo)

loo_accuracy = np.mean(loo_scores)
print(f"Fisher LDA LOO Accuracy: {loo_accuracy:.4f}")
print(f"Number of correctly classified samples: {int(loo_accuracy * len(y_values))} / {len(y_values)}")
# Plot CM for LOO (using all predictions from all samples)
plot_confusion_matrix(np.array(all_y_true_loo), np.array(all_y_pred_loo), "Fisher LDA Confusion Matrix (Leave-One-Out)", "fisher_lda_confusion_loo.jpg")
# Plot the comprehensive "Summary" plot for LOO
plot_loo_summary(np.array(all_y_true_loo), np.array(all_y_pred_loo), "Fisher LDA - Leave-One-Out Summary", "fisher_lda_loo_summary.jpg")


# 3. 结果可视化与分析
print("\n--- 3. Visualization and Analysis ---")

# Plot comparison of accuracies from different methods
methods = ['Hold-out', f'{k_folds}-Fold CV', 'Leave-One-Out']
accuracies = [accuracy_ho, np.mean(cv_scores_fisher), loo_accuracy]

plt.figure(figsize=(10, 6))
bars = plt.bar(methods, accuracies, color=['skyblue', 'lightgreen', 'salmon'])
plt.ylim(0.0, 1.1) # Accuracy is between 0 and 1
plt.ylabel('Accuracy')
plt.title('Fisher LDA Performance Comparison Across Data Splitting Methods')
plt.grid(axis='y', linestyle='--', alpha=0.7)

# Add value labels on bars
for bar, acc in zip(bars, accuracies):
    plt.text(bar.get_x() + bar.get_width() / 2, bar.get_height() + 0.01,
             f'{acc:.4f}', ha='center', va='bottom')

plt.tight_layout()
plt.savefig('image/fisher_lda_comparison.jpg')
plt.show()

# 4. 结果总结
print("\n--- 4. Summary ---")
print(f"Dataset: classification.csv ({X_values.shape[0]} samples, {X_values.shape[1]} features, {len(np.unique(y_values))} classes)")
print(f"Fisher LDA Accuracy (Hold-out, 80/20 split): {accuracy_ho:.4f}")
print(f"Fisher LDA Mean CV Accuracy ({k_folds}-fold): {np.mean(cv_scores_fisher):.4f} (+/- {np.std(cv_scores_fisher) * 2:.4f})")
print(f"Fisher LDA LOO Accuracy: {loo_accuracy:.4f}")
