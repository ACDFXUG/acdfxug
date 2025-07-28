#include <iostream>
#include <map>
#include <unordered_map>

int main(){
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    std::cout.tie(NULL);
    int N;
    std::cin>>N;
    std::map<int,int> num_cnt;
    for(int i=1,num;i<=N;++i){
        std::cin>>num;
        ++num_cnt[num];
    }
    std::unordered_map<int,int> prefix;
    int sum=0;
    for(const auto &[num,cnt]:num_cnt){
        sum+=cnt;
        prefix[num]=sum;
    }
    for(int H=N;H>=0;--H){
        auto it=num_cnt.lower_bound(H);
        int total;
        if(it==num_cnt.begin()){
            total=prefix[num_cnt.rbegin()->first];
        }else{
            total=prefix[num_cnt.rbegin()->first]-prefix[std::prev(it)->first];
        }
        if(total>=H){
            std::cout<<H<<'\n';
            return 0;
        }
    }
}