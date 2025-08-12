#include <iostream>
#include <unordered_map>
#include <vector>
#include <queue>

struct CCTV{
    std::unordered_map<int,std::vector<int>> adj;
    CCTV(int n):adj(n){}

    /**
     * @brief 对图进行拓扑排序
     * @return 拓扑排序的列表
     */
    auto topological_sort(){
        std::vector<int> topo;
        std::unordered_map<int,int> indegree;
        for(const auto &[u,v]:adj){
            if(!indegree.contains(u)) indegree[u]=0;
            for(const auto &w:v) ++indegree[w];
        }
        std::queue<int> bfs;
        for(const auto &[u,v]:indegree){
            if(v==0) bfs.push(u);
        }
        while(!bfs.empty()){
            const int u=bfs.front();
            bfs.pop();
            topo.push_back(u);
            for(const auto &w:adj[u]){
                --indegree[w];
                if(indegree[w]==0) bfs.push(w);
            }
        }
        return topo;
    }
};

int main(){
    int n;
    std::cin>>n;
    CCTV cctv(n);
    for(int i=0,x,m;i<n;++i){
        std::cin>>x>>m;
        cctv.adj[x].reserve(m);
        for(int j=0,y;j<m;++j){
            std::cin>>y;
            cctv.adj[x].push_back(y);
        }
    }
    auto topo=cctv.topological_sort();
    if(topo.size()==cctv.adj.size()) std::cout<<"YES"<<std::endl;
    else std::cout<<cctv.adj.size()-topo.size()<<std::endl;
}