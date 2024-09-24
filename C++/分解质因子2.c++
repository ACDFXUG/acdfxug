#include <iostream>
#include <list>
typedef long long ll;

std::list<ll> prime_factors(ll n){
    std::list<ll> factors;
    for(;!(n&1);n>>=1){
        factors.push_back(2ll);
    }
    for(ll i=3;i*i<=n;i+=2ll){
        for(;!(n%i);n/=i){
            factors.push_back(i);
        }
    }
    if(n>1){
        factors.push_back(n);
    }
    return factors;
}

int main(){
    int T;
    std::cin>>T;
    for(ll n;T-->0;){
        scanf("%lld",&n);
        for(ll &i:prime_factors(n)){
            printf("%lld ",i);
        }
        printf("\n");
    }
}