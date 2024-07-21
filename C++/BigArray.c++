#include <iostream>
#include <queue>
typedef std::priority_queue<int> pqi;

int get(int k,pqi pq){
    pqi p=pq;
    for(;p.size()>k;p.pop());
    return p.top();
}

int main(){
    pqi pq=pqi(std::less<int>());
    int N,K;
    std::cin>>N>>K;
    int ai,bi;
    for(int i=N;i-->0;){ 
        scanf("%d%d",&ai,&bi);
        for(;bi-->0;pq.push(ai));
    }
    std::cout<<get(K,pq)<<std::endl;
}