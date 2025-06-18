#include <iostream>
#include <bitset>

static std::bitset<10000> prime;

bool is_lucky_prime(int num){
    for(;num>0;num/=10){
        if(!prime[num]) return false;
    }
    return true;
}

int main(){
    prime.set();
    prime[0]=prime[1]=0;
    for(int p=2;p*p<10000;++p){
        if(prime[p]){
            for(int i=p*p;i<10000;i+=p){
                prime[i]=0;
            }
        }
    }
    int m,n;
    std::cin>>m>>n;
    for(int i=m;i<=n;++i){
        if(is_lucky_prime(i)) printf("%d\n",i);
    }
}