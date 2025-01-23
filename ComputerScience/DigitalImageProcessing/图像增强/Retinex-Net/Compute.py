import numpy as np
import cv2 as cv
from skimage.metrics._structural_similarity \
import structural_similarity as ssim
#pylint: disable=no-member

def SSIM(image1,image2)->float:
    return ssim(image1,image2,channel_axis=2,multichannel=True)

def PSNR(image1,image2)->float:
    mse=np.mean((image1-image2)**2)
    if mse==0:
        return 100
    return 20*np.log10(255.0/np.sqrt(mse))

if __name__ == "__main__":
    for index in range(1,4):
        lowImg=cv.imread(f"../LOLdataset/{index}low.png")
        highImg=cv.imread(f"../LOLdataset/{index}high.png")
        resLowHighImg=cv.imread(f"./EnhancedImage/{index}low.jpg")
        _,w,_=resLowHighImg.shape
        resImg=resLowHighImg[:,w>>1:]
        cv.imwrite(f"./EnhancedImage/Enhanced{index}-PSNR{PSNR(highImg,resImg):.2f}-SSIM{SSIM(highImg,resImg):.2f}.png",resImg)