#include <iostream>
#include <cstring>
using namespace std;
unsigned int POWER(unsigned int a,unsigned int x){
    unsigned int pow=1;
    for(int i=1;i<=x;i++){
        pow*=a;
    }
    return pow;
}
string EXCHANGE(string num){
    string ex="";
    for(int i=0;i<32;i++){
        ex+=(i<16)?num[i+16]:num[i-16];
    }
    return ex;
}

string toBINSTR(unsigned int n){
    string res="";
    for(;n>0;n/=2){
        int R=n%2;
        res=to_string(R)+res;
    }
    return res;
}

string toDECSTR(string bin){
    unsigned int dec=0;
    for(int i=bin.length()-1;i>=0;i--){
        if(bin[i]=='1'){
            dec+=POWER(2,bin.length()-1-i);
        }
    }
    return to_string(dec);
}

int main(){
    unsigned int n;
    scanf("%d",&n);
    string bin=toBINSTR(n);
    int L=bin.length();
    for(int i=0;i<32-L;i++){
        bin="0"+bin;
    }
    string BIN=EXCHANGE(bin);
    printf("%s",toDECSTR(BIN).c_str());
    return 0;
}