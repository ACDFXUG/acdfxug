#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

int main(){
    size_t N;
    std::cin>>N;
    std::vector<std::pair<size_t,size_t>> intervals;
    const size_t max_len=(0.5l+sqrtl(0.25l+2*N));
    for(size_t len=2;len<=max_len;++len){
        size_t remain=N-(len*(len-1))/2;
        if(remain%len==0&&remain>0){
            size_t L=remain/len;
            intervals.emplace_back(L,L+len-1);
        }
    }
    std::sort(intervals.begin(),intervals.end(),
    [](const std::pair<size_t,size_t> &a,decltype(a) b){
        return a.first>b.first;
    });
    for(const auto &[l,r]:intervals){
        std::cout<<l<<' '<<r<<'\n';
    }
}