import cv2 as cv
import numpy as np
import matplotlib.pyplot as plt
from skimage.metrics._structural_similarity \
import structural_similarity as ssim
#pylint: disable=no-member

def SSIM(image1,image2):
    return ssim(image1,image2,channel_axis=2,multichannel=True)

def PSNR(image1,image2):
    mse=np.mean((image1-image2)**2)
    if mse==0:
        return 100
    return 20*np.log10(255.0/np.sqrt(mse))

def replaceZeros(image):
    mask=image==0
    min_nozero=min(image[np.nonzero(image)])
    image[mask]=min_nozero
    return image

def SSR(srcImg,size:int):
    LBlur=replaceZeros(cv.GaussianBlur(srcImg,(size,size),0))
    img=replaceZeros(srcImg)

    dstImg=cv.log(img/255.0)
    dstLBlur=cv.log(LBlur/255.0)

    dstIL=cv.multiply(dstImg,dstLBlur)
    logR=cv.subtract(dstImg,dstIL)

    dstR=cv.normalize(logR,None,0,255,cv.NORM_MINMAX) # type: ignore
    return cv.convertScaleAbs(dstR)
    

if __name__=="__main__":
    IMG_INDEX=3
    SIZE=3

    LOWPATH=f"../LOLdataset/{IMG_INDEX}low.png"
    HIGHPATH=f"../LOLdataset/{IMG_INDEX}high.png"
    
    lowImg=cv.imread(LOWPATH)
    highImg=cv.imread(HIGHPATH)

    b,g,r=cv.split(lowImg)
    b=SSR(b,SIZE)
    g=SSR(g,SIZE)
    r=SSR(r,SIZE)
    resImg=cv.merge([b,g,r])
    cv.imwrite(f"./EnhancedImage/Enhanced{IMG_INDEX}-PSNR{PSNR(highImg,resImg):.2f}-SSIM{SSIM(highImg,resImg):.2f}.png",resImg)

    plt.figure(figsize=(10, 5))
    plt.subplot(1, 2, 1)
    plt.title('Original Image')
    plt.imshow(cv.cvtColor(highImg, cv.COLOR_BGR2RGB))
    plt.axis('off')

    plt.subplot(1, 2, 2)
    plt.title('SSR Enhanced Image')
    plt.imshow(cv.cvtColor(resImg, cv.COLOR_BGR2RGB))
    plt.axis('off')

    plt.show()