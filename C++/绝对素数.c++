#include <iostream>
#include <bitset>

std::bitset<101> prime;

bool is_definite_prime(int n){
    return prime[n]&&prime[10*(n%10)+n/10];
}

int main(){
    prime.set();
    prime[0]=prime[1]=0;
    for(int p=2;p*p<=100;++p){
        if(prime[p]){
            for(int i=p*p;i<=100;i+=p){
                prime[i]=0;
            }
        }
    }
    int A,B;
    scanf("%d%d",&A,&B);
    for(int i=A;i<=B;++i){
        if(is_definite_prime(i)){
            printf("%d\n",i);
        }
    }
}