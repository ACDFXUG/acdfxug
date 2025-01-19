import math,cv2,numpy as np,matplotlib.pyplot as plt
#pylint: disable=no-member

cellSize=8
binSize=9
angleUnit=180/binSize

def globalGradient(img):
    gdtX=cv2.Sobel(img,cv2.CV_64F,1,0,ksize=5)
    gdtY=cv2.Sobel(img, cv2.CV_64F,0,1,ksize=5)
    gdtMag,gdtAgl=cv2.cartToPolar(gdtX,gdtY,angleInDegrees=True)
    gdtAgl[gdtAgl>180.]-=180
    return gdtMag,gdtAgl
def getClosestBins(gdtAgl):
    idx=int(gdtAgl/angleUnit)
    mod=gdtAgl%angleUnit
    return idx%binSize,(idx+1)%binSize,mod/angleUnit

def cellGradient(cellMag,cellAgl):
    orientationCtrs=np.zeros(binSize)
    for i in range(cellMag.shape[0]):
        for j in range(cellMag.shape[1]):
            gdtStrength=cellMag[i][j]
            gdtAgl=cellAgl[i][j]
            minAgl,maxAgl,w=getClosestBins(gdtAgl)
            orientationCtrs[minAgl]+=gdtStrength*(1-w)
            orientationCtrs[maxAgl]+=gdtStrength*w
    return orientationCtrs

def renderGradient(img,cellGdt):
    cellWidth=cellSize/2
    maxMag=np.array(cellGdt).max()
    for i in range(cellGdt.shape[0]):
        for j in range(cellGdt.shape[1]):
            cellGrd=cellGdt[i][j]/maxMag
            agl=0
            aglGap=angleUnit
            for mag in cellGrd:
                aglRadian=math.radians(agl)
                x1=int(i*cellSize+cellWidth+mag*cellWidth*math.cos(aglRadian))
                y1=int(j*cellSize+cellWidth+mag*cellWidth*math.sin(aglRadian))
                x2=int(i*cellSize+cellWidth-mag*cellWidth*math.cos(aglRadian))
                y2=int(j*cellSize+cellWidth-mag*cellWidth*math.sin(aglRadian))
                cv2.line(img,(x1,y1),(x2,y2),int(255*math.sqrt(mag))) # type: ignore
                agl+=aglGap
    return img

def HOG(grayImg):
    h,w=grayImg.shape
    gdtMag,gdtAgl=globalGradient(grayImg)
    gdtMag=abs(gdtMag)
    cellGdtVector=np.zeros((h//cellSize,w//cellSize,binSize))
    for i in range(cellGdtVector.shape[0]):
        for j in range(cellGdtVector.shape[1]):
            cellMag=gdtMag[i*cellSize:(i+1)*cellSize,j*cellSize:(j+1)*cellSize]
            cellAgl=gdtAgl[i*cellSize:(i+1)*cellSize,j*cellSize:(j+1)*cellSize]
            cellGdtVector[i][j]=cellGradient(cellMag,cellAgl)
    hogImage=renderGradient(np.zeros([w,h]),cellGdtVector)
    hogVector=[]
    for i in range(cellGdtVector.shape[0]-1):
        for j in range(cellGdtVector.shape[1]-1):
            blockVector=[]
            blockVector.extend(cellGdtVector[i][j])
            blockVector.extend(cellGdtVector[i][j+1])
            blockVector.extend(cellGdtVector[i+1][j])
            blockVector.extend(cellGdtVector[i+1][j+1])
            mag=math.sqrt(sum(i**2 for i in blockVector))+1e-5
            if mag!=0:
                blockVector=[i/mag for i in blockVector]
            hogVector.append(blockVector)
    return np.asarray(hogVector),hogImage

if __name__=="__main__":
    PATH="./test/test1.jpg"
    IMG=cv2.imread(PATH)
    # WIDTH=64
    # HEIGHT=128
    h,w,_=IMG.shape
    IMG_RE=IMG[:,:][:,:,::-1]
    # IMG_RE=cv2.resize(IMG_RE,(600,(600*h)//w))
    gray=cv2.cvtColor(IMG_RE,cv2.COLOR_BGR2GRAY)

    plt.figure(figsize=(6.4,2.0*3.2))
    plt.subplot(1,2,1)
    plt.imshow(IMG_RE)

    hogVector,hogImage=HOG(gray)

    plt.subplot(1,2,2)
    hogImage=hogImage.transpose()
    plt.imshow(hogImage,cmap=plt.cm.gray)
    cv2.imwrite(f"./HogImage/{PATH.rsplit('/', maxsplit=1)[-1][:-4]}_hog.jpg",hogImage)
    plt.show()