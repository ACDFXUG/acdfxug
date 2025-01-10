#include <iostream>
#include <cmath>
#include <print>

size_t pow(size_t x,int n){
    size_t ans=1;
    for(;n>0;n>>=1){
        if(n&1) ans*=x;
        x*=x;
    }
    return ans;
}

int main(){
    int k,l;
    std::cin>>k>>l;
    int start=log(l)/log(k);
    for(int exp=start-1;pow(k,exp)<=0x7fffffffuz;exp++){
        if(pow(k,exp)==l){
            std::println("YES\n{}",exp-1);
            return 0;
        }
    }
    std::println("NO\n");
}