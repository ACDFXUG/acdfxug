#include <iostream>

int digitSquareSum(size_t n){
    int sum=0;
    for(int digit;n>0;n/=10){
        digit=n%10;
        sum+=digit*digit;
    }
    return sum;
}

int main(){
    size_t k,a,b;
    std::cin>>k>>a>>b;
    if(k>b){
        std::cout<<0<<std::endl;
        return 0;
    }
    size_t mins=(a+k-1)/k;
    if(mins>8100){
        std::cout<<0<<std::endl;
        return 0;
    }
    /*find numbers of n that 
    k*digitSquareSum(n)==n&&a<=n<=b*/
    int count=0;
    for(size_t s=mins;s<=8100;++s){
        size_t n=k*s;
        if(n>b) break;
        if(digitSquareSum(n)==s)
            ++count;
    }
    std::cout<<count<<std::endl;
}