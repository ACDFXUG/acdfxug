#include <iostream>
#include <map>

int main(){
    int T;
    std::cin>>T;
    for(int n,m;T-->0;){
        std::cin>>n>>m;
        std::map<int,int> cities;//cities[x]表示需要x天的城市数
        for(int i=0,x;i<m;++i){
            std::cin>>x;
            ++cities[x];
        }
        int cnt=0;
        for(const auto &entry:cities){
            if(n>=entry.first){
                cnt+=entry.second;
                n-=entry.first;
            }
        }
        std::cout<<cnt<<std::endl;
    }
}