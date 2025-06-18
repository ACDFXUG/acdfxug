#include <iostream>
#include <unordered_map>
#include <map>

int main(){
    std::map<int,int,std::greater<int>> score;
    std::unordered_map<int,int> rank;
    int n,r=1,p;
    std::cin>>n;
    for(int x;n-->0;){
        std::cin>>x;
        ++score[x];
    }
    for(const auto &[scr,cnt]:score){
        if(!rank.contains(scr)){
            rank[scr]=r;
            r+=cnt;
        }
    }
    std::cin>>p;
    std::cout<<rank[p]<<'\n';
}