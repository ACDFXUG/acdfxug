#include <iostream>
#include <vector>
#include <print>
using namespace std::ranges;

struct Bit{
    uint8_t val:1;
    Bit(int x=1):val(x){}
};

int countPrimes(int n) {
    if(n<=2) return 0;
    Bit *is_prime=new Bit[n+1]();
    is_prime[0].val=is_prime[1].val=0;
    for(int prime=2;prime*prime<=n;++prime){//质数筛法
        if(is_prime[prime].val){
            for(int i=prime*prime;i<=n;i+=prime){
                is_prime[i].val=0;
            }
        }
    }
    int ans=0;
    for(int i=2;i<n;++i){
        if(is_prime[i].val){
            ans++;
        }
    }
    delete[] is_prime;
    return ans;
}

int main(){
    int n;
    scanf("%d",&n);
    std::println("{}",countPrimes(n));
}