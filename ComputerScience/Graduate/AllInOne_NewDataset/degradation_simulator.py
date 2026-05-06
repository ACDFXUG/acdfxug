# import os
# os.environ["KMP_DUPLICATE_LIB_OK"] = "TRUE"
# import cv2
# import torch
# import torch.nn.functional as F
# import random
# import matplotlib.pyplot as plt
# from dataset import MedicalRestorationDataset

# # 在真实数据集中，我们直接使用观察值作为输入，无需模拟退化
# # 此文件保留为空，或者仅保留一个占位函数

# def apply_realistic_degradation(*args, **kwargs):
#     """
#     在真实数据集中，我们不使用模拟退化
#     观察值已经存在于数据集中，直接使用即可
#     """
#     raise NotImplementedError("在真实数据集中不需要使用模拟退化")

# if __name__ == '__main__':
#     test_dataset = MedicalRestorationDataset('../MRI-Images-of-Brain-Tumor/timri/test')
#     test_loader = torch.utils.data.DataLoader(test_dataset, batch_size=1, shuffle=False)
#     clean,_= next(iter(test_loader))
#     degraded = apply_realistic_degradation(clean, deg_type='mixed', severity='heavy')
#     plt.imshow(clean[0,0].cpu(),cmap='gray')
#     plt.show()
#     plt.imshow(degraded[0,0].cpu(),cmap='gray')
#     plt.show()

    