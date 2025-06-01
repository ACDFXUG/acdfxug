#include <iostream>
#include <set>
#include <vector>

void println(std::set<int> &s){
    if(s.empty())[[unlikely]] std::cout<<'\n';
    else{
        for(auto it=s.begin(),last=--s.end();it!=s.end();++it){
            std::cout<<*it<<(it!=last?' ':'\n');
        }
    }
}

int main(){
    std::ios::sync_with_stdio(false);
    std::cin.tie(0);
    std::cout.tie(0);

    std::vector<std::set<int>> graph;
    int T;
    std::cin>>T;
    for(int n,m;T-->0;){
        graph.clear();
        std::cin>>n>>m;
        graph.resize(n+1,{});
        for(int u,v;m-->0;){
            std::cin>>u>>v;
            graph[u].insert(v);
        }
        for(int vtx=1;vtx<=n;println(graph[vtx++]));
    }
}