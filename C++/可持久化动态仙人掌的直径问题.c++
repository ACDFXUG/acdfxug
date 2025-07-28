#include <iostream>
#include <cmath>
using ld=long double;

int main(){
    int n,m;
    std::cin>>n>>m;
    ld logn=logl(n);
    ld sup=expl(logn/m);
    int cnt=0;
    for(int i=1;i<=sup;++i){
        ++cnt;
    }
    std::cout<<cnt<<std::endl;
}