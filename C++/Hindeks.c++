#include <iostream>
#include <map>
#include <unordered_map>
#include <vector>

int main(){
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    std::cout.tie(NULL);
    int N;
    std::cin>>N;
    std::map<int,int> num_cnt;
    std::unordered_map<int,std::map<int,int>::iterator> num_cnt_it;
    std::vector<int> suffix;
    for(int i=1,num;i<=N;++i){
        std::cin>>num;
        ++num_cnt[num];
    }
    int sum=0;
    for(auto it=num_cnt.rbegin();it!=num_cnt.rend();++it){
        sum+=it->second;
        suffix.push_back(sum);
    }
    for(int H=N;H>=0;--H){
        auto it=num_cnt.lower_bound(H);
        auto idx=std::distance(num_cnt.begin(),it);
        if(suffix[idx]>=H){
            std::cout<<H<<"\n";
            return 0;
        }
    }
    
}