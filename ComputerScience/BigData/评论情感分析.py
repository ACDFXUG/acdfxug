import os
os.environ["HF_ENDPOINT"] = "https://hf-mirror.com"
from transformers import pipeline

classifier=pipeline(task='text-classification',\
    model='./training_dir/checkpoint-669')

def analyze_sentiment(text):
    result=classifier(text)
    # sentiment={
    #     'POSITIVE':0,
    #     'NEGATIVE':0,
    #     'NEUTRAL':0
    # }
    # for res in result:
    #     label=res['label']
    #     if label=='LABEL_1':
    #         sentiment['POSITIVE']+=1
    #     elif label=="LABEL_0":
    #         sentiment['NEGATIVE']+=1
    #     else:
    #         sentiment['NEUTRAL']+=1
    #     # print(res)
    # return sentiment
    return result[0]['label']

if __name__ == "__main__":
    with open('俄乌战争评论组合.txt','r',encoding="utf-8") as f:
        # print(analyze_sentiment([line for line in f if len(line)<150]))
        with open('commentAnalyze.txt','w',encoding="utf-8") as f2:
            for line in f:
                if len(line) <250:
                    label=analyze_sentiment(line)
                    if label=='LABEL_1':
                        f2.write("+++"+line)
                    elif label=="LABEL_0":
                        f2.write("---"+line)
                    else:
                        f2.write("~~~"+line)
    #{'POSITIVE': 5079, 'NEGATIVE': 6814, 'NEUTRAL': 856}