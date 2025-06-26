#include <iostream>
#include <algorithm>
#include <tuple>
#include <cmath>

template<int R>
bool is_suitable_for(int n){
    for(;n>0;n/=R){
        if(n%R==0) return false;
    }
    return true;
}

int main(){
    int a,b,count{};
    std::cin>>a>>b;
    for(int i=a;i<=b;++i){
        if(is_suitable_for<5>(i)
            &&is_suitable_for<7>(i)
            &&is_suitable_for<9>(i))[[unlikely]]{
            ++count;
        }
    }
    std::cout<<count<<std::endl;
}