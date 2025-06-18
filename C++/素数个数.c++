#include <iostream>
#include <bitset>

std::bitset<100000001> prime;

int main(){
    int N,count=0;
    scanf("%d",&N);
    prime.set();
    prime[0]=prime[1]=0;
    for(int p=2;p*p<=N;++p){
        if(prime[p]){
            for(int i=p*p;i<=N;i+=p){
                prime[i]=0;
            }
        }
    }
    for(int i=2;i<=N;count+=prime[i++]);
    printf("%d\n",count);
}