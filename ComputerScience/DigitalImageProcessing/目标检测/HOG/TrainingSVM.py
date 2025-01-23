import joblib,glob,os,cv2
from sklearn.svm import LinearSVC
from sklearn.model_selection import GridSearchCV,train_test_split
from sklearn import svm,metrics
import numpy as np
from sklearn.preprocessing import LabelEncoder
from skimage.feature._hog import hog
#pylint: disable=no-member

trainData=[]
trainLabels=[]

positiveImagePath='DATAIMAGE/positive/'
negativeImagePath='DATAIMAGE/negative/'
modelPath='models/models.dat'

for pos in glob.glob(os.path.join(positiveImagePath,'*.png')):
    fd=cv2.imread(pos,0)
    fd=cv2.resize(fd,(64,128))
    fd=hog(fd,orientations=9,pixels_per_cell=(8,8),visualize=False,cells_per_block=(3,3))
    trainData.append(fd)
    trainLabels.append(1)

for neg in glob.glob(os.path.join(negativeImagePath,'*.jpg')):
    fd=cv2.imread(neg,0)
    fd=cv2.resize(fd,(64,128))
    fd=hog(fd,orientations=9,pixels_per_cell=(8,8),visualize=False,cells_per_block=(3,3))
    trainData.append(fd)
    trainLabels.append(0)

trainData=np.float32(trainData)
trainLabels=np.array(trainLabels)

print('Data Loaded--------')
print('Train Data:',len(trainData))
print('Train Labels (1,0)',len(trainLabels))

print('''Classification with SVM''')

model=LinearSVC(max_iter=3000)
print('Training...... SVM')
model.fit(trainData,trainLabels)
joblib.dump(model,modelPath)
print(f"model saved :{modelPath}")