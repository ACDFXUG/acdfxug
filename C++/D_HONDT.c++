#include <iostream>
#include <map>
#include <unordered_map>
int main(){
    int x,n;
    std::cin>>x>>n;
    std::unordered_map<char,int> vote;
    std::map<double,char,std::greater<double>> points;
    std::map<char,int> count;
    for(int i=1;i<=n;i++){
        char party;
        int cnt;
        std::cin>>party>>cnt;
        if(cnt*20>=x){
            vote[party]=cnt;
            for(double i=1;i<=14;i++){
                points[cnt/i]=party;
            }
        }
    }
    auto it=points.begin();
    for(int i=0;i<14;i++){
        ++count[it++->second];
    }
    for(auto &[party,cnts]:count){
        printf("%c %d\n",party,cnts);
    }
}