#include <iostream>
#include <bitset>
#include <vector>

std::bitset<10000001> prime;
std::vector<int> primes;

int get_prime_factors_count(int n){
    int cnt=0;
    for(const auto &p:primes){
        for(;n%p==0;){
            if(n>=p)[[likely]]{
                n/=p;
                ++cnt;
            }else{
                break;
            }
        }
        if(n<p) break;
    }
    return cnt;
}
#include <print>
int main(){
    prime.set();
    prime[0]=prime[1]=0;
    primes.reserve(700000);
    for(int i=2;i*i<=10000000;++i){
        if(prime[i]){
            for(int j=i*i;j<=10000000;j+=i){
                prime[j]=0;
            }
        }
    }
    for(int i=2;i<=10000000;++i){
        if(prime[i]) primes.push_back(i);
    }
    int N,M,max_cnt=0;
    std::cin>>N>>M;
    for(int i=N;i<=M;++i){
        max_cnt=std::max(max_cnt,get_prime_factors_count(i));
    }
    std::cout<<max_cnt<<std::endl;
}