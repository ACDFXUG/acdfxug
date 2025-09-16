#include <iostream>
#include <unordered_set>
#include <queue>

int main(){
    int n;
    std::cin>>n;
    std::unordered_set<int> visited;
    std::queue<std::pair<int,int>> path;
    path.emplace(1,0);
    visited.insert(1);
    while(!path.empty()){
        const auto [cur,step]=path.front();
        path.pop();
        if(cur==n){
            std::cout<<step<<std::endl;
            return 0;
        }
        if(int add=cur+1;add<=n&&!visited.contains(add)){
            path.emplace(add,step+1);
            visited.insert(add);
        }
        if(int mul=cur<<1;mul<=n&&!visited.contains(mul)){
            path.emplace(mul,step+1);
            visited.insert(mul);
        }
        if(int sub=cur-1;sub>0&&!visited.contains(sub)){
            path.emplace(sub,step+1);
            visited.insert(sub);
        }
    }
}