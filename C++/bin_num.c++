#include <iostream>

int BinToDec(std::string bin){
    int L=bin.length(),dec=0;
    for(int i=0;i<L;i++){
        dec+=(bin[i]=='1')?1<<(L-1-i):0;
    }
    return dec;
}
int main(){
    std::string bin;
    std::cin>>bin;
    int t=0,DEC=BinToDec(bin);
    for(;DEC!=1;t++){
        DEC&1==1?DEC+=1:DEC/=2;
    }
    std::cout<<t<<std::endl;
}