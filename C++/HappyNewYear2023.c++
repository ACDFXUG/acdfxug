#include <iostream>
#include <unordered_map>

auto get_prime_factors(size_t N){
    std::unordered_map<size_t,short> primes;
    for(;(N&1)==0;N>>=1){
        primes[2]++;
    }
    for(size_t i=3;i*i<=N;i+=2){
        for(;N%i==0;N/=i){
            primes[i]++;
        }
    }
    if(N>1){
        primes[N]++;
    }
    return primes;
}

int main(){
    short T;
    std::cin>>T;
    for(size_t N;T-->0;){
        scanf("%zu",&N);
        auto primes=get_prime_factors(N);
        int p=0,q=0;
        for(const auto &[prime,count]:primes){
            if(count==1){
                q=prime;
            }
            if(count==2){
                p=prime;
            }
            if(p>0&&q>0){
                printf("%d %d\n",p,q);
                break;
            }
        }

    }
}