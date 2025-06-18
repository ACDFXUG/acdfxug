#include <iostream>
#include <bitset>

static std::bitset<1001> prime;

int main(){
    prime.set();
    prime[0]=prime[1]=0;
    for(int p=2;p*p<=1000;++p){
        if(prime[p]){
            for(int i=p*p;i<=1000;i+=p){
                prime[i]=0;
            }
        }
    }
    int A,B,count=0;
    scanf("%d%d",&A,&B);
    for(int p=A;p<=B;++p){
        if(prime[p]) ++count;
    }
    printf("%d\n",count);
}