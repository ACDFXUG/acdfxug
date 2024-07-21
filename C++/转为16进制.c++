#include <iostream>
std::string toHex(int num){
    unsigned int ans=(unsigned int)num;
    std::string radix="0123456789abcdef",ANS="";
    for(;ans>0;ans>>=4){
        ANS=radix[ans%16]+ANS;
    }
    return num==0?"0":ANS;
}
int main(){
    int num;
    std::cin>>num;
    std::cout<<toHex(num)<<std::endl;
}

