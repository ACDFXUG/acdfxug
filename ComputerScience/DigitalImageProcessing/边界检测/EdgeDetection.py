# import matplotlib.pyplot as plt
import cv2
import numpy as np
from torch import uint8
# from matplotlib.figure import Figure
from utils.FDoG import run

def add_gaussian_noise(image, mean=0, var=0.01):
    # 将图像转换为浮点型
    image = np.array(image / 255, dtype=float)

    # 生成高斯噪声
    noise = np.random.normal(mean, var ** 0.5, image.shape)

    # 将噪声添加到图像
    noisy_image = image + noise

    # 将图像裁剪到0-1范围
    noisy_image = np.clip(noisy_image, 0, 1)

    # 将图像转换回8位无符号整型
    noisy_image = np.uint8(noisy_image * 255)

    return noisy_image
#pylint: disable=no-member
def edge_detection(path:str)->None:
    img = cv2.imread(path) 
    gray_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

    # Prewitt
    kernelx = np.array([[1, 1, 1],
                        [0, 0, 0],
                        [-1, -1, -1]], dtype=int)
    kernely = np.array([[-1, 0, 1],
                        [-1, 0, 1],
                        [-1, 0, 1]], dtype=int)
    x = cv2.filter2D(gray_img, cv2.CV_16S, kernelx)
    y = cv2.filter2D(gray_img, cv2.CV_16S, kernely)
    absX = cv2.convertScaleAbs(x)
    absY = cv2.convertScaleAbs(y)
    edge_prewitt = cv2.addWeighted(absX, 0.5, absY, 0.5, 0)
    cv2.imwrite(f"{path[:-4]}_prewitt.jpg", edge_prewitt)
    # Sobel
    x = cv2.Sobel(gray_img, cv2.CV_16S, 1, 0)
    y = cv2.Sobel(gray_img, cv2.CV_16S, 0, 1)
    absX = cv2.convertScaleAbs(x)
    absY = cv2.convertScaleAbs(y)
    edge_sobel = cv2.addWeighted(absX, 0.5, absY, 0.5, 0)
    cv2.imwrite(f"{path[:-4]}_sobel.jpg", edge_sobel)
    # Canny
    edge_canny = cv2.Canny(gray_img, 50, 150)
    cv2.imwrite(f"{path[:-4]}_canny.jpg", edge_canny)

    #fDoG
    edge_fDoG = run(
        img=gray_img, sobel_size=3,
        etf_iter=6, etf_size=7,
        fdog_iter=5, sigma_c=1.0, rho=0.997, sigma_m=3.0,
        tau=0.907
    )
    cv2.imwrite(f"{path[:-4]}_fDoG.jpg", edge_fDoG)


if __name__ == "__main__":
    img_path = "C:/Users/yaoyu/Desktop/4.png"
    edge_detection(img_path)

