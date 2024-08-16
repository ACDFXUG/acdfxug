#include <iostream>
#include <unordered_map>
#include <algorithm>
#include <vector>
#define left first
#define right second

struct Vote{
    int vote_counts=0;
};

int main(){
    int T;
    std::cin>>T;
    while(T-->0){
        int n;
        scanf("%d",&n);
        std::unordered_map<int,Vote> vote;
        for(int i=0,x;i<n;i++){
            scanf("%d",&x);
            vote[x].vote_counts++;
        }
        std::vector<std::pair<int,Vote>> VOTE;
        for(auto &p:vote){
            VOTE.push_back(p);
        }
        std::sort(
            VOTE.begin(),
            VOTE.end(),
            [](auto &v1,auto &v2){
                int cnt1=v1.right.vote_counts;
                int cnt2=v2.right.vote_counts;
                return cnt1==cnt2?v1.left<v2.left:cnt1>cnt2;
        });
        int counts=0,max=VOTE.begin()->right.vote_counts;
        for(auto &v:VOTE){
            if(v.right.vote_counts==max){
                counts++;
            }
        } 
        if(counts==VOTE.size()){
            printf("-1\n");
        }else{
            printf("%d\n",counts);
            for(auto &v:VOTE){
                if(v.right.vote_counts==max){
                    printf("%d ",v.left);
                }
            }
            printf("\n");
        }
    }
}