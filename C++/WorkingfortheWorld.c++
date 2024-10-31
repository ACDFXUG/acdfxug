#include <iostream>
#include <set>

auto get_prime_factors(size_t n){
    std::set<size_t> primes;
    for(;(n&1)==0;n>>=1){
        primes.insert(2uz);
    }
    for(auto i=3uz;i*i<=n;i+=2uz){
        for(;n%i==0;n/=i){
            primes.insert(i);
        }
    }
    if(n>1uz){primes.insert(n);}
    return primes;
}

int main(){
    for(size_t n;scanf("%zu",&n),n!=0uz;){
        auto primes=get_prime_factors(n);
        printf("%zu\n",*primes.rbegin());
    }
}