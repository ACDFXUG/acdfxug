#include <iostream>
#include <vector>
#include <unordered_set>

const std::unordered_set<int> primes={
    2,3,5,7,11,13,17,19,23,
    29,31,37,41,43,47,53,59,
    61,67,71,73,79,83,89,97
};

int get_digit_sum(size_t n){
    int ans=0;
    for(;n;n/=10){
        ans+=n%10uz;
    }
    return ans;
}

int main(){
    size_t L,R;
    scanf("%zu%zu",&L,&R);
    std::vector<size_t> square;
    for(size_t i=1,s;(s=i*i)<=R;i++){
        if(s>=L){
            square.push_back(s);
        }
    }
    __int128_t ans=1;
    for(const auto &s:square){
        if(primes.contains(get_digit_sum(s))){
            ans*=s;
            ans%=998244353;
        }
    }
    if(ans==1) printf("0\n");
    else std::cout<<size_t(ans%998244353)<<std::endl;
}