from ImageInterpolation import *
from ProcessImage import paint_image,get_mask
#pylint: disable=no-member

if __name__ == "__main__":
    PATH="./task1Image/hfh.jpg"
    img=cv.imread(PATH)
    IMAGE=cv.cvtColor(img,cv.COLOR_BGR2RGB)
    #20,20,200,100
    #120,20,400,100
    #200,400,400,100
    MASK=get_mask(120,20,400,100,IMAGE)
    cv.imwrite(f"{PATH[:-4]}_mask.jpg",MASK)
    PAINTED=paint_image(IMAGE,MASK)/255
    plt.imsave(f"{PATH[:-4]}_painted.jpg",PAINTED)

    BI=bilinear(PAINTED,2)
    BI=bilinear(BI,0.5)
    BI=bilinear(BI,2)
    BI=bilinear(BI,0.5)
    plt.imsave(f"{PATH[:-4]}_bilinear.jpg",BI)
    plt.imshow(BI)
    plt.show()

    NN=nearest_neighbor(PAINTED,1)
    plt.imsave(f"{PATH[:-4]}_nearest_neighbor.jpg",NN)
    plt.imshow(NN)
    plt.show()

    RBF=rbf(PAINTED,1)
    plt.imsave(f"{PATH[:-4]}_rbf.jpg",RBF)
    plt.imshow(RBF)
    plt.show()