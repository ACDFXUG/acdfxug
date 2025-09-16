#include <iostream>
#include <queue>
#include <set>
#include <print>

int main(){
    int n;
    std::cin>>n;
    std::priority_queue<size_t,
        std::vector<size_t>,std::greater<size_t>
    > bfs;
    std::set<size_t> visited;
    constexpr int factor[]{2,3,5,7};
    bfs.push(1);
    visited.insert(1);
    while(!bfs.empty()){
        size_t cur=bfs.top();
        bfs.pop();
        if(visited.size()==20001){
            break;
        }
        for(int i=0;i<4;++i){
            size_t next=cur*factor[i];
            if(!visited.contains(next)){
                bfs.push(next);
                visited.insert(next);
            }
            if(visited.size()==20001) break;
        }
    }
    std::println("{}",*std::next(visited.begin(),n-1));
}