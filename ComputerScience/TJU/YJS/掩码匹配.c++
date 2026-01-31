#include <iostream>
#include <vector>

int main(){
    int mem;
    std::cin>>mem;
    std::vector<int> nums(mem);
    for(int i=0;i<mem;std::cin>>nums[i++]);
    int q;
    std::cin>>q;
    for(int s,t,m;q-->0;){
        std::cin>>s>>t>>m;
        int cnt=0;
        for(int i=s-1;i<t;++i){
            cnt+=(nums[i]&m)==m;
        }
        std::cout<<cnt<<std::endl;
    }
}