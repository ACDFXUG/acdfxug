#include <iostream>
#include <unordered_map>
#include <set>

void println(std::set<int> &s){
    for(auto it=s.begin(),last=--s.end();it!=s.end();++it){
        if(it!=last) std::cout<<*it<<" ";
        else std::cout<<*it<<'\n';
    }
}

struct Graph{
    std::unordered_map<int,std::set<int>> adj;
    int vertex,edge;
    Graph(int v,int e):vertex(v),edge(e){}
    void add_edge(int src,int dst){
        adj[src].insert(dst);
        adj[dst].insert(src);
    }
};

void println(Graph &g){
    for(int i=1;i<=g.vertex;++i){
        for(int j=1;j<=g.vertex;++j){
            std::cout<<g.adj[i].contains(j)<<(j==g.vertex?'\n':' ');
        }
    }
}

int main(){
    int v,e;
    std::cin>>v>>e;
    Graph g(v,e);
    for(int i=0,src,dst;i<e;++i){
        std::cin>>src>>dst;
        g.add_edge(src,dst);
    }
    println(g);
    for(int i=1;i<=v;++i){
        std::cout<<g.adj[i].size()<<' ';
        println(g.adj[i]);
    }
}