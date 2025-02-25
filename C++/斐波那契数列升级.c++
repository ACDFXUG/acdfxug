#include <iostream>
#include <unordered_map>

template<class K,class V>
using hash_map=std::unordered_map<K,V>;

size_t fibonacci(const int &n){
    static size_t fibo[50]{};
    fibo[0]=0,fibo[1]=1;
    for(int i=2;i<=n;++i){
        fibo[i]=fibo[i-1]+fibo[i-2];
    }
    return fibo[n];
}

hash_map<int,int> get_prime_factors(int n){
    hash_map<int,int> factors;
    for(;n&1;n>>=1){
        ++factors[2];
    }
    for(int i=3;i*i<=n;i+=2){
        for(;n%i==0;n/=i){
            ++factors[i];
        }
    }
    if(n>1){
        ++factors[n];
    }
    return factors;
}

int main(){
    int n;
    scanf("%d",&n);
    const auto fibo=fibonacci(n);
    const auto factors=get_prime_factors(fibo);
    std::string result;
    for(const auto &[key,value]:factors){
        
    }
}