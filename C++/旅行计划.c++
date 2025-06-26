#include <iostream>
#include <unordered_map>
#include <unordered_set>
#include <stack>

template<class K,class V>
using hashmap=std::unordered_map<K,V>;

template<class T>
using hashset=std::unordered_set<T>;


struct Travel{
    hashmap<int,hashset<int>> cities;
    hashmap<int,int> max_cnt;
    Travel(const int &n):cities(n){}
    void add_road(const int &src,const int &dst){
        cities[src].insert(dst);
    }
    int DFS(const int &start){
        if(max_cnt.contains(start)) return max_cnt[start];
        hashset<int> visited;
        std::stack<std::pair<int,int>> dfs;
        int max=1;
        dfs.emplace(start,1);
        visited.insert(start);
        while(!dfs.empty()){
            const auto [city,cnt]=dfs.top();
            dfs.pop();
            for(const auto &next:cities[city]){
                if(!visited.contains(next)){
                    visited.insert(next);
                    int nxt=DFS(next);
                    max=std::max(max,cnt+nxt);
                    dfs.emplace(next,cnt+1);
                }
            }
        }
        max_cnt[start]=max;
        return max;
    }
    int highDFS(const int &start,hashset<int> &visited){
        if(max_cnt.contains(start)) return max_cnt[start];
        visited.insert(start);
        int max=1;
        for(const int &next:cities[start]){
            if(!visited.contains(next)){
                int nxt=highDFS(next,visited);
                max=std::max(max,nxt+1);
            }
        }
        visited.erase(start);
        max_cnt[start]=max;
        return max;
    }
};
#include <vector>
int main(){
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);

    int n,m;
    std::cin>>n>>m;
    Travel travel(n);
    for(int x,y,i=1;i<=m;++i){
        std::cin>>x>>y;
        travel.add_road(y,x);
    }
    std::vector<int> max_cnt(n+1,0);
    hashset<int> visited;
    for(int i=n;i>0;--i){
        max_cnt[i]=travel.highDFS(i,visited);
    }
    for(int s=1;s<=n;++s){
        std::cout<<max_cnt[s]<<'\n';
    }
}