import matplotlib.pyplot as plt
import os
os.environ["KMP_DUPLICATE_LIB_OK"] = "TRUE"
import torch
from dataset import MedicalRestorationDataset, get_data_loaders
from unet_plusplus_adaptive import UNetPlusPlusAdaptive
from MPRNet import MPRNet

# ==================== 配置区 ====================
BASE_DATA_ROOT = '../LoDoPaB-CT'  # LoDoPaB-CT数据集根目录
MODEL_PATH = './checkpoints/unet_final.pth'  # 训练好的模型路径
MODEL_TYPE = 'Ours'  # 可选 'Ours' 或 'MPRNet'
RESULT_SAVE_PATH = "restoration_result.png"  # 结果保存路径

# 加载测试数据
_, _, test_loader = get_data_loaders(BASE_DATA_ROOT, train_size=1, val_size=1, test_size=5, batch_size=1)

# 加载模型
if MODEL_TYPE == 'Ours':
    model = UNetPlusPlusAdaptive()
    print(f"✅ 已选择模型: UNet++ 自适应模型")
elif MODEL_TYPE == 'MPRNet':
    model = MPRNet(in_c=1, out_c=1, n_feat=20, scale_unetfeats=12, scale_orsnetfeats=8, num_cab=4)
    print(f"✅ 已选择模型: MPRNet")
else:
    raise ValueError(f"❌ 未知的模型类型: {MODEL_TYPE}")

model.load_state_dict(torch.load(MODEL_PATH, map_location='cpu'))
model.eval()

# 取一张图测试
try:
    inputs, targets = next(iter(test_loader))
except StopIteration:
    print("❌ 无法从数据加载器获取数据，请检查数据集路径和文件")
    exit()

with torch.no_grad():
    restored_img = model(inputs)

# 绘图对比
plt.figure(figsize=(15, 5))

plt.subplot(1, 3, 1)
plt.imshow(inputs[0, 0].cpu(), cmap='gray')
plt.title("Reconstructed Sinogram Input")
plt.axis('off')

plt.subplot(1, 3, 2)
plt.imshow(targets[0, 0].cpu(), cmap='gray')
plt.title("Ground Truth")
plt.axis('off')

plt.subplot(1, 3, 3)
plt.imshow(restored_img[0, 0].cpu(), cmap='gray')
plt.title("Restored Output")
plt.axis('off')

plt.tight_layout()
plt.savefig(RESULT_SAVE_PATH, dpi=300, bbox_inches='tight')
plt.show()

print(f"📊 结果已保存为 {RESULT_SAVE_PATH}")