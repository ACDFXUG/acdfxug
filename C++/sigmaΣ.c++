#include <iostream>
#include <unordered_set>

int main(){
    size_t N,K;
    scanf("%zu%zu",&N,&K);
    size_t ans=K*(K+1)>>1;
    std::unordered_set<size_t> dup;
    for(size_t Ai;N-->0;){
        scanf("%zu",&Ai);
        if(Ai>=1&&Ai<=K&&
        !dup.contains(Ai)){
            ans-=Ai;
            dup.insert(Ai);
        }
    }
    printf("%zu\n",ans);
}