#include <iostream>
#include <bitset>

std::bitset<10001> prime;

int main(){
    prime.set();
    prime[0]=prime[1]=0;
    for(int p=2;p*p<=10000;++p){
        if(prime[p]){
            for(int i=p*p;i<=10000;i+=p){
                prime[i]=0;
            }
        }
    }
    int n,m,r,k,rank=0;
    std::cin>>n>>m>>r>>k;
    for(int p=n;p>=0;--p){
        if(prime[p]&&p%m==r){
            ++rank;
            if(rank==k){
                std::cout<<p<<'\n';
                goto ret;
            }
        }
    }
    printf("-1\n");
    ret:return 0;
}