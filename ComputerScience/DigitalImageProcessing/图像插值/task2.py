from ImageInterpolation import *
from ProcessImage import drop_pixels
from skimage.metrics._structural_similarity \
import structural_similarity as ssim
#pylint: disable=no-member

if __name__ == "__main__":
    
    PATH="./task2Image/avatar.jpg"
    img=cv.imread(PATH)
    IMAGE=cv.cvtColor(img,cv.COLOR_BGR2RGB)
    IMAGE=IMAGE/255  # 归一化

    # DROPPED=drop_pixels(IMAGE,0.8)
    # plt.imsave(f"{PATH[:-4]}_dropped.jpg",DROPPED)
    # plt.imshow(DROPPED)
    # plt.show()

    # BI=bilinear(bilinear(DROPPED,0.5),2)
    # plt.imsave(f"{PATH[:-4]}_bilinear.jpg",BI)
    # plt.imshow(BI)
    # plt.show()

    # NN=nearest_neighbor(DROPPED,1)
    # plt.imsave(f"{PATH[:-4]}_nearest_neighbor.jpg",NN)
    # plt.imshow(NN)
    # plt.show()
    
    # RBF=rbf(DROPPED,1)
    # plt.imsave(f"{PATH[:-4]}_rbf.jpg",RBF)
    # plt.imshow(RBF)
    # plt.show()


    BI=[]
    NN=[]
    RBF=[]

    for i in range(1,10):
        dropped=drop_pixels(IMAGE,i/10)
        plt.imshow(dropped)
        plt.show()
        BI.append(bilinear(bilinear(dropped,0.5),2))
        NN.append(nearest_neighbor(dropped,1))
        RBF.append(rbf(dropped,1))

    SSIM_BI=[]
    SSIM_NN=[]
    SSIM_RBF=[]
    L2_BI=[]
    L2_NN=[]
    L2_RBF=[]
    for i in range(9):
        ssim_bi=ssim(IMAGE,BI[i],channel_axis=2,data_range=1)
        ssim_nn=ssim(IMAGE,NN[i],channel_axis=2,data_range=1)
        ssim_rbf=ssim(IMAGE,RBF[i],channel_axis=2,data_range=1)
        SSIM_BI.append(ssim_bi)
        SSIM_NN.append(ssim_nn)
        SSIM_RBF.append(ssim_rbf)

        l2_bi=np.mean((IMAGE-BI[i])**2)
        l2_nn=np.mean((IMAGE-NN[i])**2)
        l2_rbf=np.mean((IMAGE-RBF[i])**2)
        L2_BI.append(l2_bi)
        L2_NN.append(l2_nn)
        L2_RBF.append(l2_rbf)

    X=np.linspace(0.1,0.9,9)

    plt.figure(figsize=(12,6))
    plt.subplot(1, 2, 1)
    plt.plot(X, SSIM_BI, label='Bilinear Interpolation', marker='o')
    plt.plot(X, SSIM_NN, label='Nearest Neighbor', marker='s')
    plt.plot(X, SSIM_RBF, label='RBF Interpolation', marker='^')
    plt.title('SSIM Value vs. Dropout Rate')
    plt.xlabel('Dropout Rate')
    plt.ylabel('SSIM Value')
    plt.legend()
    plt.grid(True)
    plt.savefig(f"{PATH[:-4]}_ssim.jpg")
    plt.show()

    plt.subplot(1, 2, 2)
    plt.plot(X, L2_BI, label='Bilinear Interpolation', marker='o')
    plt.plot(X, L2_NN, label='Nearest Neighbor', marker='s')
    plt.plot(X, L2_RBF, label='RBF Interpolation', marker='^')
    plt.title('L2 Norm (MSE) vs. Dropout Rate')
    plt.xlabel('Dropout Rate')
    plt.ylabel('L2 Norm (MSE)')
    plt.legend()
    plt.grid(True)
    plt.savefig(f"{PATH[:-4]}_l2.jpg")
    plt.show()
    