#include <iostream>
#include <bitset>
#include <vector>
#include <cmath>

std::bitset<100000001> primes;

bool special_prime(int n){
    for(;n>0;n/=10){
        if(!primes[n])
            return false;
    }
    return true;
}

auto get_len_special_primes(const int &len){
    std::vector<int> result;
    int min=std::pow(10,len-1),max=std::pow(10,len)-1;
    for(int i=min;i<=max;i++){
        if(special_prime(i))
            result.push_back(i);
    }
    return result;
}

int main(){
    primes.set();
    primes[0]=primes[1]=0;
    for(int p=2;p*p<=100000001;p++){
        if(primes[p]){
            for(int i=p*p;i<=100000001;i+=p)
                primes[i]=0;
        }
    }
    int n;
    scanf("%d",&n);
    auto result=get_len_special_primes(n);
    for(const int &ans:result){
        printf("%d\n",ans);
    }
}