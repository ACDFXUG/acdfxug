#include <iostream>
#include <unordered_map>

auto prime_factors_count(int n){
    std::unordered_map<int,int> factor_cnt;
    for(;!(n&1);n>>=1){
        factor_cnt[2]++;
    }
    for(int i=3;i*i<=n;i+=2){
        for(;!(n%i);n/=i){
            factor_cnt[i]++;
        }
    }
    if(n>2){
        factor_cnt[n]++;
    }
    return factor_cnt;
}

int factor_count(int n){
    int ans=1;
    for(auto &[prime,cnt]:
    prime_factors_count(n)){
        ans*=cnt+1;
    }
    return ans;
}

int main(){
    int n,ans=1;
    std::cin>>n;
    for(int i=2;i<=n;i++){
        ans+=factor_count(i);
    }
    std::cout<<ans<<std::endl;
}