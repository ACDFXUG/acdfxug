#include <iostream>
#include <queue>
#include <unordered_set>
#include <print>

int minimumOperationsToMakeEqual(int x, int y) {
    std::unordered_set<int> visited;
    std::queue<std::pair<int,int>> bfs;
    bfs.emplace(x,0);
    visited.insert(x);
    while(!bfs.empty()){
        const auto [cur,step]=bfs.front();
        std::println("[cur:{},step:{}]",cur,step);
        bfs.pop();
        if(cur==y) return step;
        if(cur%11==0){
            if(!visited.contains(cur/11)){
                bfs.emplace(cur/11,step+1);
                visited.insert(cur/11);
            }
        }
        if(cur%5==0){
            if(!visited.contains(cur/5)){
                bfs.emplace(cur/5,step+1);
                visited.insert(cur/5);
            }
        }
        if(!visited.contains(cur+1)){
            bfs.emplace(cur+1,step+1);
            visited.insert(cur+1);
        }
        if(!visited.contains(cur-1)){
            bfs.emplace(cur-1,step+1);
            visited.insert(cur-1);
        }
    }
    return -1;
}

int main(){
    std::cout<<minimumOperationsToMakeEqual(12,1)<<std::endl;
}