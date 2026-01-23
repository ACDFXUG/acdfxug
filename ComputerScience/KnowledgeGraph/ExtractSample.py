import pandas as pd

df = pd.read_csv("triples.csv")
sample = df.sample(n=250, random_state=42) 
sample["IsCorrect"] = ""
sample.to_csv("evaluation_sample.csv", index=False, encoding="utf-8")
