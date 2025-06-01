#include <iostream>
#include <vector>
#include <map>

int find_mode(const std::vector<int> &arr,int from,int to){
    std::map<int,int> count;
    for(int i=from-1;i<to;++i) ++count[arr[i]];
    int max_cnt=0,mode=0;
    for(const auto &[num,cnt]:count){
        if(cnt>max_cnt){
            max_cnt=cnt;
            mode=num;
        }
    }
    return mode;
}

int main(){
    int n,m;
    std::cin>>n>>m;
    std::vector<int> arr(n);
    for(int i=0;i<n;std::cin>>arr[i++]);
    for(int flag,x,y;m-->0;){
        std::cin>>flag>>x>>y;
        if(flag==0) std::cout<<find_mode(arr,x,y)<<'\n';
        else arr[x-1]=y;
    }
}