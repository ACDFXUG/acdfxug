#include <iostream>
#include <cmath>
#include <print>

int main(){
    size_t s;
    std::cin>>s;
    size_t a=0x7fffffff,L=0;
    const size_t max=size_t(sqrtl(s<<1))+1;
    for(size_t i=1,j=max;i<=j;++i,--j){
        const size_t sumi=(i*(i-1))>>1;
        const size_t sumj=(j*(j-1))>>1;
        if(s>sumj&&(s-sumj)%j==0){
            if(a>(s-sumj)/j) a=(s-sumj)/j,L=j;
        }
        if(s>sumi&&(s-sumi)%i==0){
            if(a>(s-sumi)/i) a=(s-sumi)/i,L=i;
        }
    }
    std::println("{} {}",a,a+L-1);
}