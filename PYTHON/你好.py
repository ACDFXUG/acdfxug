import torch

# 检查CUDA是否可用
print(torch.version.cuda)        # 显示 PyTorch 编译时使用的 CUDA 版本（如 '12.1'）
print(torch.cuda.is_available()) # 是否能调用 CUDA