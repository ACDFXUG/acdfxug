#include <iostream>
#include <cmath>

constexpr size_t weird_func(const size_t &x){
    return x*std::log10(x);
}

int main(){
    int n;
    std::cin>>n;
    //weird_func(x)>=n-1,find x
    //1<=n<=2e9,so need efficient algorithm
    //use binary search
    size_t left=1,right=n;
    while(left<right){
        size_t mid=(left+right)>>1;
        if(weird_func(mid)>=n-1){
            right=mid;
        }else{
            left=mid+1;
        }
    }
    std::cout<<left<<std::endl;
}