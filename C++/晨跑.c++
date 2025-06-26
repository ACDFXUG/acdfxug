#include <iostream>
#include <unordered_map>

std::unordered_map<int,int> prime_factors(int n){
    std::unordered_map<int,int> factors;
    for(;(n&1)==0;n>>=1) ++factors[2];
    for(int p=3;p*p<=n;p+=2){
        for(;n%p==0;n/=p) ++factors[p];
    }
    if(n>1) ++factors[n];
    return factors;
}

template<class T>
auto combine(T &pf1,T &pf2,T &pf3){
    std::unordered_map<int,int> comfactors;
    for(const auto &[p,cnt]:pf1){
        comfactors[p]+=cnt;
    }
    for(const auto &[p,cnt]:pf2){
        comfactors[p]=std::max(comfactors[p],cnt);
    }
    for(const auto &[p,cnt]:pf3){
        comfactors[p]=std::max(comfactors[p],cnt);
    }
    return comfactors;
}

int main(){
    int a,b,c;
    std::cin>>a>>b>>c;
    auto pf1=prime_factors(a),
        pf2=prime_factors(b),
        pf3=prime_factors(c);
    auto com=combine(pf1,pf2,pf3);
    size_t ans=1;
    for(const auto &[p,cnt]:com){
        for(int i=0;i<cnt;++i) ans*=p;
    }
    std::cout<<ans<<std::endl;
}