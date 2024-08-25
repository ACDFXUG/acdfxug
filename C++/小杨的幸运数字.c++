#include <iostream>
#include <set>

std::set<int> prime_factors(int n){
    std::set<int> factors;
    for(;(n&1)==0;n>>=1){
        factors.insert(2);
    }
    for(int i=3;i*i<=n;i+=2){
        for(;n%i==0;n/=i){
            factors.insert(i);
        }
    }
    if(n>2){
        factors.insert(n);
    }
    return factors;
}

int main(){
    int n,x;
    std::cin>>n;
    while(n-->0){
        scanf("%d",&x);
        printf(prime_factors(x).size()==2?"1\n":"0\n");
    }
}