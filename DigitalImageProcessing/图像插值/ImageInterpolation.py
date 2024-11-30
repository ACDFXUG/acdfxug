import cv2 as cv
import numpy as np
import matplotlib.pyplot as plt
from scipy.spatial import KDTree

#pylint: disable=no-member
def bilinear(srcImg,scale:float):
    src_height, src_width = srcImg.shape[:2]

    # 计算目标尺寸
    target_width = int(src_width * scale)
    target_height = int(src_height * scale)

    # 创建目标图像的空数组
    target_image = np.zeros((target_height, target_width, 3), dtype=np.float32)

    # 计算缩放比例
    scale_x = src_width / target_width
    scale_y = src_height / target_height

    for y in range(target_height):
        for x in range(target_width):
            # 计算目标像素在源图像中的浮点坐标
            src_x = (x + 0.5) * scale_x - 0.5
            src_y = (y + 0.5) * scale_y - 0.5

            # 找到源图像中最近的四个像素点的坐标
            x0 = int(np.floor(src_x))
            x1 = min(x0 + 1, src_width - 1)
            y0 = int(np.floor(src_y))
            y1 = min(y0 + 1, src_height - 1)

            # 检查四个像素是否为黑色
            is_black_00 = np.all(srcImg[y0, x0, :] == 0)
            is_black_01 = np.all(srcImg[y0, x1, :] == 0)
            is_black_10 = np.all(srcImg[y1, x0, :] == 0)
            is_black_11 = np.all(srcImg[y1, x1, :] == 0)

            # 如果所有四个像素都是黑色，跳过该像素
            if is_black_00 and is_black_01 and is_black_10 and is_black_11:
                continue

            # 计算权重
            w1=(x1-src_x)*(y1-src_y) if not is_black_00 else 0
            w2=(src_x-x0)*(y1-src_y) if not is_black_01 else 0
            w3=(x1-src_x)*(src_y-y0) if not is_black_10 else 0
            w4=(src_x-x0)*(src_y-y0) if not is_black_11 else 0

            # 计算总权重
            total_weight = w1 + w2 + w3 + w4

            # 如果总权重为零，跳过该像素
            if total_weight == 0:
                continue

            # 计算目标像素的颜色值
            target_image[y,x,:]=(w1*srcImg[y0,x0,:]+w2*srcImg[y0,x1,:]\
                +w3*srcImg[y1,x0,:]+w4*srcImg[y1,x1,:])/total_weight

    return target_image

    # srcH, srcW, _ = srcImg.shape
    # dstH=int(srcH*scale)
    # dstW=int(srcW*scale)
    # # 将原图像的高度和宽度扩展一个像素
    # # 目的是为了防止后面的计算出现数组越界的情况
    # srcImg = np.pad(srcImg, ((0,1),(0,1),(0,0)), mode='reflect')
    # # 创建目标图像
    # dstImg = np.zeros((dstH, dstW, 3))
    # # 遍历目标图像中的每个像素点
    # for dstX in range(dstH):
    #     for dstY in range(dstW):
    #         # 寻找目标图像上的一个点对应在原图像上的位置 (x, y)
    #         # 注意这里的x和y不是一个整数
    #         x = dstX * (srcH / dstH)
    #         y = dstY * (srcW / dstW)
    #         # 将x和y进行向下取整，得到原图上对应的像素位置(scrX, srcY)
    #         scrX = int(x)
    #         srcY = int(y)
    #         # 计算目标像素与原图像上整数像素之间的距离
    #         u = x - scrX
    #         v = y - srcY
    #         # 计算目标像素值，通过原图像四个整数像素的加权和
    #         dstImg[dstX, dstY] = (1-u) * (1-v) * srcImg[scrX,   srcY  ] + \
    #                              u     * (1-v) * srcImg[scrX+1, srcY  ] + \
    #                              (1-u) * v     * srcImg[scrX,   srcY+1] + \
    #                              u     * v     * srcImg[scrX+1, srcY+1]
    # return dstImg
                
def find_nearest_nonzero(image, x, y):
    """ 查找给定点 (x, y) 最近的非零像素 """
    h, w, _ = image.shape
    for r in range(max(0, x-1), min(h, x+2)):
        for c in range(max(0, y-1), min(w, y+2)):
            if not np.all(image[r, c] == 0):
                return image[r, c]
    # 如果周围都没有非零像素，继续扩大搜索范围
    for dist in range(2, max(h, w)):
        for r in range(max(0, x-dist), min(h, x+dist+1)):
            for c in range(max(0, y-dist), min(w, y+dist+1)):
                if not np.all(image[r, c] == 0):
                    return image[r, c]
    # 如果所有像素都是零，返回原点值
    return image[x, y]


def nearest_neighbor(image,scale:float):
    h, w, channels = image.shape
    new_h, new_w = int(h * scale), int(w * scale)
    scaled_image = np.zeros((new_h, new_w, channels), dtype=image.dtype)
    
    for y in range(new_h):
        for x in range(new_w):
            old_x = int(x / scale)
            old_y = int(y / scale)
            if np.all(image[old_y, old_x] == 0):
                # 如果原始位置是零，查找最近的非零像素
                scaled_image[y, x] = find_nearest_nonzero(image, old_y, old_x)
            else:
                # 直接使用原始像素值
                scaled_image[y, x] = image[old_y, old_x]
    
    return scaled_image
       
def rbf(image,scale:float,epsilon=1.0,k_neighbors=10):
    h, w, channels = image.shape
    new_h, new_w = int(h * scale), int(w * scale)
    scaled_image = np.zeros((new_h, new_w, channels), dtype=image.dtype)
    
    # 获取所有非零像素的位置和值
    non_zero_indices = np.argwhere(np.any(image != 0, axis=-1))
    non_zero_values = image[non_zero_indices[:, 0], non_zero_indices[:, 1]]
    
    # 将非零像素的位置归一化到 [0, 1] 范围内
    non_zero_positions = non_zero_indices / np.array([h-1, w-1])
    
    # 构建 K-D Tree
    tree = KDTree(non_zero_positions)
    
    for y in range(new_h):
        for x in range(new_w):
            # 计算目标像素在原图中的位置
            old_x=x/(new_w-1)
            old_y=y/(new_h-1)
            target_position = np.array([[old_x,old_y]])
            
            # 找到最近的 k 个非零像素
            distances, indices = tree.query(target_position,k=k_neighbors)
            
            # 如果没有找到足够的邻居，直接跳过
            if len(indices)==0:
                continue
            
            # 计算 RBF 值
            rbf_values=np.exp(-(distances**2)/(2*epsilon**2))
            
            # 计算插值值
            for c in range(channels):
                weighted_sum=np.sum(rbf_values * non_zero_values[indices, c])
                total_weight=np.sum(rbf_values)
                if total_weight>0:
                    scaled_image[x,y,c]=weighted_sum/total_weight
    
    return scaled_image


