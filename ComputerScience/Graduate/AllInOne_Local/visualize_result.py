import os
os.environ["KMP_DUPLICATE_LIB_OK"] = "TRUE"
import torch
import matplotlib.pyplot as plt
from dataset import MedicalRestorationDataset, transform
from unet_plusplus_adaptive import UNetPlusPlusAdaptive
from degradation_simulator import apply_realistic_degradation
from MPRNet import MPRNet
MODEL_TYPE='Ours'
USE_SPATIAL=True


# 加载测试集
test_dataset = MedicalRestorationDataset('../MRI-Images-of-Brain-Tumor/timri/test', transform=transform)
test_loader = torch.utils.data.DataLoader(test_dataset, batch_size=1, shuffle=True)

# 加载模型
# model = UNetPlusPlusAdaptive()
if MODEL_TYPE=='Ours':
    model=UNetPlusPlusAdaptive()
elif MODEL_TYPE=='MPRNet':
    model=MPRNet(in_c=1,out_c=1,n_feat=20,scale_unetfeats=12,scale_orsnetfeats=8,num_cab=4)
model.load_state_dict(torch.load("./checkpoints/unet_final.pth", map_location='cpu'))
model.eval()

# 取一张图测试
clean_img, _ = next(iter(test_loader))
degraded_img = apply_realistic_degradation(clean_img, deg_type='mixed',severity='medium',spatial_degradation=USE_SPATIAL)

with torch.no_grad():
    restored_img = model(degraded_img)

# 绘图对比
plt.figure(figsize=(15, 5))

plt.subplot(1, 3, 1)
plt.imshow(clean_img[0, 0].cpu(), cmap='gray')
plt.title("Original (GT)")
plt.axis('off')

plt.subplot(1, 3, 2)
plt.imshow(degraded_img[0, 0].cpu(), cmap='gray')
plt.title("Degraded Input")
plt.axis('off')

plt.subplot(1, 3, 3)
plt.imshow(restored_img[0, 0].cpu(), cmap='gray')
plt.title("Restored Output")
plt.axis('off')

plt.tight_layout()
plt.savefig("restoration_result.png", dpi=300)
plt.show()

print("📊 结果已保存为 restoration_result.png")