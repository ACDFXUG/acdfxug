#include <iostream>
#include <unordered_set>

const std::unordered_set<short> prime={
    2,3,5,7,11,13,17,19,23
};

short sum_digit(short n){
    short sum=0;
    for(;n;n/=10){
        sum+=n%10;
    }
    return sum;
}

int main(){
    int T;
    std::cin>>T;
    for(short N;T-->0;){
        std::cin>>N;
        short cnt=0;
        for(short i=1;i<=N;++i){
            if(prime.count(sum_digit(i))){
                ++cnt;
            }
        }
        std::cout<<cnt<<std::endl;
    } 
}