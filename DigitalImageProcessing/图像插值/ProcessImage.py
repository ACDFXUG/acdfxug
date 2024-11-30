import cv2 as cv
import numpy as np
#pylint: disable=no-member
def drop_pixels(image,drop_rate:float):
    h,w,_=image.shape
    mask=np.random.rand(h,w)<drop_rate
    tmp=image.copy()
    tmp[mask,:]=0
    return tmp

def get_mask(start_x,start_y
             ,end_x,end_y,image):
    h,w=image.shape[:2]
    mask=np.ones((h,w),dtype=np.uint8)*255
    line_thickness=2
    cv.line(mask,(start_x,start_y),(end_x,end_y),color=(0,0,0),thickness=line_thickness)
    return mask

def paint_image(image,mask):
    masked=cv.bitwise_and(image,image,mask=mask)
    return masked