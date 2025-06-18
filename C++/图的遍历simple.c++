#include <iostream>
#include <unordered_map>
#include <unordered_set>
#include <vector>
#include <queue>

struct Graph{
    std::unordered_map<int,std::unordered_set<int>> adj;
    const int vertex,edge;
    Graph(int n,int m):vertex(n),edge(m){
        adj.reserve(n);
    }
    void add_edge(int src,int dst){
        adj[src].insert(dst);
    }
    std::vector<int> bfs(const int &start){
        std::vector<int> path;
        std::unordered_set<int> visited;
        std::queue<int> bfs;
        bfs.push(start);
        while(!bfs.empty()){
            int vtx=bfs.front();
            bfs.pop();
            if(!visited.contains(vtx)){
                visited.insert(vtx);
                path.push_back(vtx);
                for(const int &v:adj[vtx]) bfs.push(v);
            }
        }
        return path;
    }
};

int main(){
    std::unordered_map<int,int> max;
    int n,m;
    std::cin>>n>>m;
    Graph g(n,m);
    for(int i=0,u,v;i<m;++i){
        std::cin>>u>>v;
        g.add_edge(v,u);// 反向添加边
    }
    for(int start=n;start>=1;--start){
        auto path=g.bfs(start);
        for(const int &v:path){
            if(max.contains(v))[[likely]]
                max[v]=std::max(max[v],start);
            else max[v]=start;
        }
    }
    for(int i=1;i<=n;++i){
        std::cout<<max[i]<<(i==n?'\n':' ');
    }
}