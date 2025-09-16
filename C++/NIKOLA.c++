#include <iostream>
#include <queue>
#include <vector>
#include <print>
#include <unordered_set>

struct State{
    int loc;
    int step;
    int cost;
    bool forward;
    State(int loc,int step,int cost,bool forward):
    loc(loc),step(step),cost(cost),forward(forward){}
};

int main(){
    int n;
    std::cin>>n;
    std::vector<int> a(n+1);
    for(int i=1;i<=n;std::cin>>a[i++]);
    std::queue<State> bfs;
    bfs.emplace(1,0,0,true);
    while(!bfs.empty()){
        auto [loc,step,cost,forward]=bfs.front();
        bfs.pop();
        if(loc==n){
            std::println("{}",cost);
            return 0;
        }
        if(forward){
            if(loc+step+1<=n){
                bfs.emplace(loc+step+1,step+1,cost+a[loc+step+1],true);
                bfs.emplace(loc+step+1,step+1,cost+a[loc+step+1],false);
            }
        }else{
            if(loc!=1){
                if(loc-step>=1){
                    bfs.emplace(loc-step,step,cost+a[loc-step],true);
                    bfs.emplace(loc-step,step,cost+a[loc-step],false);
                }
            } 
        }
    }
}