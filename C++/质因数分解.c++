#include <iostream>
#include <vector>

int main(){
    int n;
    scanf("%d",&n);
    std::vector<int> primes;
    for(;(n&1)==0;n>>=1){
        primes.push_back(2);
    }
    for(int i=3;i*i<=n;i+=2){
        for(;n%i==0;n/=i){
            primes.push_back(i);
        }
    }
    if(n>1){
        primes.push_back(n);
    }
    printf("%d\n",std::max(primes[0],primes[1]));
}