import numpy as np,cv2,joblib,Sliding as sd
from imutils.object_detection import non_max_suppression
from skimage.feature._hog import hog
from skimage import color
from skimage.transform.pyramids import pyramid_gaussian
#pylint: disable=no-member

imagePath="./test/test3.jpg"
image=cv2.imread(imagePath)
h,w,_=image.shape
image=cv2.resize(image,(300,300*h//w))
size=(64,128)
stepSize=(9,9)
downScale=1.25

detections=[]

scale=0
model=joblib.load("models/models.dat")

for imScaled in pyramid_gaussian(image,downscale=downScale):
    if imScaled.shape[0]<size[1] or imScaled.shape[1]<size[0]:
        break
    for (x,y,win) in sd.sliding_window(imScaled,size,stepSize):
        if win.shape[0]!=size[1] or win.shape[1]!=size[0]:
            continue
        win=color.rgb2gray(win)
        fd=hog(win,orientations=9,pixels_per_cell=(8,8),visualize=False,cells_per_block=(3,3))
        fd=fd.reshape(1,-1)
        pre=model.predict(fd)
        if pre==1:
            if model.decision_function(fd)>0.5:
                detections.append((int(x * (downScale**scale)),
                                    int(y * (downScale**scale)),
                                    model.decision_function(fd),
                                    int(size[0] * (downScale**scale)),
                                    int(size[1] * (downScale**scale))))
    scale+=1
    
clone=image.copy()
rects=np.array([[x,y,x+w,y+h] for (x,y,_,w,h) in detections])
sc=[score[0] for (_,_,score,_,_) in detections]

print("score:",sc)
sc=np.array(sc)
pick=non_max_suppression(rects,probs=sc,overlapThresh=0.1)

for (x1,y1,x2,y2) in pick:
    cv2.rectangle(clone,(x1,y1),(x2,y2),(0,255,0),2)
    cv2.putText(clone,'Person',(x1-2,y1-2),1,0.75,(121,12,34),1)
cv2.imwrite(f'output/{imagePath.rsplit('/', maxsplit=1)[-1]}',clone)
