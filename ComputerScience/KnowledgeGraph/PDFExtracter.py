import pdfplumber
import os

def extractText(pdfPath:str):
    path=os.path.splitext(pdfPath)[0]
    output=path+".txt"
    txt=[]
    with pdfplumber.open(pdfPath) as pdf:
        print(f"Totally {pdf.pages} pages")
        for i,page in enumerate(pdf.pages):
            text=page.extract_text()
            if text:
                txt.append(text)
            else: txt.append("")
            print(f"Extracted page {i+1}")
    with open(output,'w',encoding='utf-8') as f:
        for i,text in enumerate(txt):
            f.write(f"===Page {i+1}===\n")
            if text:
                f.write(text)
            else: f.write("Empty Page")
            f.write("\n\n")

    print(f"Extracted text saved to {output}")
    return output

if __name__=="__main__":
    extractText("./cctv 经典枪械完全图解手册.pdf")
