#include <iostream>
#include <unordered_map>
#include <ranges>
#include <vector>
#include <algorithm>
#if __cplusplus>=202311L
#include <print>
#endif

template<class K,class V>
using hashmap=std::unordered_map<K,V>;

hashmap<int,int> prime_factors(int n){
    hashmap<int,int> factors;
    for(;(n&1)==0;n>>=1) ++factors[2];
    for(int p=3;p*p<=n;p+=2){
        for(;n%p==0;n/=p) ++factors[p];
    }
    if(n>1) ++factors[n];
    return factors;
}

void generate_factors(const hashmap<int,int> &factors,
    const std::vector<int> &primes,
    int index,int curr,
    std::vector<int> &result){
    if(index==primes.size()){
        result.push_back(curr);
        return;
    }
    const int &p=primes[index],&pw=factors.at(p);
    for(int i=0;i<=pw;++i){
        generate_factors(factors,primes,index+1,curr,result);
        curr*=p;
    }
}

std::vector<int> factorize(int n){
    hashmap<int,int> primes=prime_factors(n);
    std::vector<int> factors;
    auto keys=primes | std::ranges::views::keys;
    generate_factors(primes,std::vector(keys.begin(),keys.end()),0,1,factors);
    std::sort(factors.begin(),factors.end());
    return factors;
}

int F(int A){
    auto f=factorize(A);
    int sum=0;
    for(int i=0;i<f.size()-1;++i){
        sum+=f.back()-f[i];
    }
    return std::abs(sum);
}

int main(){
    int A,B;
    std::cin>>A>>B;
    size_t sum=0;
    for(int i=A;i<=B;++i){
        sum+=F(i);
    }
    std::cout<<sum<<std::endl;
}