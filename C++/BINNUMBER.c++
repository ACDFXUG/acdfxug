#include <iostream>
typedef std::string String;

String XOR(String a,String b){
    int l1=a.length(),l2=b.length();
    String Xor="";
    if(l1>=l2){
        b=String(l1-l2,'0')+b;
        for(int i=0;i<l1;i++){
            Xor+=(a[i]==b[i])?"0":"1";
        }
    }else{
        a=String(l2-l1,'0')+a;
        for(int i=0;i<l2;i++){
            Xor+=(a[i]==b[i])?"0":"1";
        }
    }
    return Xor;
}

String AND(String a,String b){
    int l1=a.length(),l2=b.length();
    String And="";
    if(l1>=l2){
        b=String(l1-l2,'0')+b;
        for(int i=0;i<l1;i++){
            And+=(a[i]=='1'&&b[i]=='1')?"1":"0";
            
        }
    }else{
        a=String(l2-l1,'0')+a;
        for(int i=0;i<l2;i++){
            And+=(a[i]=='1'&&b[i]=='1')?"1":"0";
        }
    }
    return And;
}

String shiftLeft(String a,int n){
    return a.append(String(n,'0'));
}

String shiftRight(String a,int n){
    return a.substr(0,a.length()-n);
}

String binary_plus_one(String bin){
    String l="",r="",b="1";
    do{
        l=XOR(bin,b);
        r=shiftLeft(AND(bin,b),1);
        bin=l;
        b=r;
    }while(r.find("1")!=-1);
    return l;
}

int main(){
    String bin;int t=0;
    std::cin>>bin;int l=bin.length();
    for(;bin!=(String(l-1,'0')+"1");t++){
        if(bin[l-1]=='1'){
            bin=binary_plus_one(bin);
        }else{
            bin=shiftRight(bin,1);
        }
        l=bin.length();
    }
    printf("%d\n",t);
}