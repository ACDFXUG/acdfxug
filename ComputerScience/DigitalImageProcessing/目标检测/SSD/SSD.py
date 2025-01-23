from torchvision.models.detection.ssd \
import ssd300_vgg16,SSD300_VGG16_Weights
import torchvision.transforms as trans
import cv2,torch
import matplotlib.pyplot as plt
#pylint: disable=no-member
SSDModel=ssd300_vgg16(weights=SSD300_VGG16_Weights.DEFAULT)
SSDModel.eval()
toTensor=trans.ToTensor()

def drawBox(image,predictions,threshold=0.5):
    boxes=predictions['boxes'].cpu().numpy()
    labels=predictions['labels'].cpu().numpy()
    scores=predictions['scores'].cpu().numpy()
    labelTxt=[]
    for i,score in enumerate(scores):
        if score>threshold and labels[i]==1:#只检测人物(label==1)
            cv2.rectangle(image,
                          (int(boxes[i][0]), int(boxes[i][1])),
                          (int(boxes[i][2]), int(boxes[i][3])),
                          (0, 255, 0), 2)
            label_text = f"Person: {score:.2f}"
            labelTxt.append(label_text)
            cv2.putText(image, label_text,(int(boxes[i][0]),int(boxes[i][1])-10),
                cv2.FONT_HERSHEY_SIMPLEX,0.9,(0,255,0),2)
    return image,labelTxt

if __name__=="__main__":
    PATH="./test/test1.jpg"
    img=cv2.imread(PATH)
    img=cv2.cvtColor(img,cv2.COLOR_BGR2RGB)
    imgTensor=toTensor(img).unsqueeze(0)
    with torch.no_grad():
        result=SSDModel(imgTensor)
    imgBoxed,labelTexts=drawBox(img,result[0])
    plt.imsave(f"./output/{PATH.rsplit('/',maxsplit=1)[-1]}",imgBoxed)
    print(labelTexts)
