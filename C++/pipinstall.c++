#include <iostream>
#include <unordered_map>
#include <unordered_set>
#include <stack>

struct Package{
    std::unordered_map<
        int,std::unordered_set<int>> dep;
    Package(int N){}
    void add_depend(int u,int v){
        dep[u].insert(v);
    }
    int DFS(int start){
        std::stack<int> dfs;
        std::unordered_set<int> visited;
        dfs.push(start);
        visited.insert(start);
        int ans=0;
        while(!dfs.empty()){
            int cur=dfs.top();
            dfs.pop();
            ++ans;
            for(const int &v:dep[cur]){
                if(!visited.contains(v)){
                    dfs.push(v);
                    visited.insert(v);
                }
            }
        }
        return ans;
    }
};

int main(){
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);
    int N;
    std::cin>>N;
    Package pkg(N);
    for(int i=1,Ki;i<=N;++i){
        std::cin>>Ki;
        for(int j=0,v;j<Ki;++j){
            std::cin>>v;
            pkg.add_depend(i,v);
        }
    }
    std::cout<<pkg.DFS(1)<<std::endl;
}