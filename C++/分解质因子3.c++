#include <iostream>
#include <cmath>
#define FAST_IO(boo1,nul1,nul2) do{\
    std::ios::sync_with_stdio(boo1);\
    std::cin.tie(nul1);\
    std::cout.tie(nul2);\
}while(0)

auto prime_factors_xor(int n){
    size_t ans=0;
    for(;!(n&1);n>>=1){
        ans^=2;
    }
    const register int sqrt_n=sqrt(n);
    for(int i=3;i<=sqrt_n;i+=2){
        for(;n%i==0;n/=i){
            ans^=i;
        }
    }
    if(n>1){
        ans^=n;
    }
    return ans;
}

int main(){
    FAST_IO(false,NULL,NULL);
    int T,n;
    std::cin>>T;
    while(T-->0){
        std::cin>>n;
        std::cout<<prime_factors_xor(n)<<'\n';
    }
}