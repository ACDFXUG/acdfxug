#include <iostream>
#include <cmath>

size_t fast_power(size_t x,int n){
    size_t ans=1;
    for(;n;n>>=1){
        if(n&1){
            ans*=x;
        }
        x*=x;
    }
    return ans;
}

int main(){
    size_t l,r,w;
    std::cin>>l>>r>>w;
    if(w==1){
        if(l<=w&&w<=r){
            std::cout<<1<<'\n';
        }else{
            std::cout<<-1<<'\n';
        }
        return 0;
    }
    int inf=std::log(l)/std::log(w);
    int sup=std::log(r)/std::log(w);
    int count=0;
    for(int i=inf;i<=sup;++i){
        auto power=fast_power(w,i);
        if(l<=power&&power<=r){
            std::cout<<power<<' ';
            ++count;
        }
    }
    if(count==0){
        std::cout<<-1<<'\n';
    }
}