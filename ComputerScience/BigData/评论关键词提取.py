import yake.yake
import jieba

def extract_keywords(text,max_ngram_size=2, deduplication_threshold=0.9, numOfKeywords=5):
    custom_kw_extractor = yake.KeywordExtractor(n=max_ngram_size, dedupLim=deduplication_threshold, top=numOfKeywords)
    keys=set()
    for txt in text:
        seg=" ".join(jieba.cut(txt))
        keyword=custom_kw_extractor.extract_keywords(seg)
        for kw,_ in keyword:
            keys.add(kw)
    return list(keys)

if __name__ == "__main__":
    with open('俄乌战争评论组合.txt','r',encoding="utf-8") as f:
        keywords=extract_keywords([l for l in f if len(l)<150])
        with open('俄乌关键词.txt','w',encoding="utf-8") as fw:
            for kws in keywords:
                fw.write(kws+'\n')