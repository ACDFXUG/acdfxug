#include <iostream>
#include <bitset>

bool contains4(int n){
    while(n){
        if(n%10==4) return true;
        n/=10;
    }
    return false;
}

std::bitset<10000001> prime;

int main(){
    prime.set();
    prime[0]=prime[1]=0;
    for(size_t i=2;i<=10000000;i++){
        if(prime[i]){
            for(size_t j=i*i;j<=10000000;j+=i){
                prime[j]=0;
            }
        }
    }
    int ans=0;
    for(int i=2;i<=10000000;i++){
        if(prime[i]&&contains4(i)){
            ++ans;
        }
    }
    std::cout<<ans;
}