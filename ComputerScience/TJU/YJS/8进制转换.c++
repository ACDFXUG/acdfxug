#include <iostream>

constexpr std::string OCT="01234567";

std::string dec2oct(int n){
    std::string res="";
    while(n){
        res=OCT[n%8]+res;
        n/=8;
    }
    return res.empty()?"0":res;
}

int main(){
    int n;
    std::cin>>n;
    std::cout<<dec2oct(n)<<std::endl;
}