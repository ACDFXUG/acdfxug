#include <iostream>
#include <map>
#include <unordered_map>
#include <vector>
#include <algorithm>

int main(){
    int x,n;
    std::cin>>x>>n;
    std::unordered_map<char,int> vote;
    std::map<char,int> count;
    std::vector<std::pair<double,char>> points;
    for(int i=1;i<=n;++i){
        char party;
        int cnt;
        std::cin>>party>>cnt;
        if(cnt*20>=x){
            vote[party]=cnt;
            for(double j=1;j<=14;++j){
                points.emplace_back(cnt/j,party);
            }
        }
    }
    std::sort(points.begin(),points.end(),[](auto &&a,auto &&b){
        return a.first>b.first;
    });
    for(auto &[point,party]:points){
        count[party]=0;
    }
    for(int i=0;i<14;++i){
        ++count[points[i].second];
    }
    for(const auto &[party,cnt]:count){
        std::cout<<party<<" "<<cnt<<"\n";
    }
}