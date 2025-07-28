#include <iostream>
#include <unordered_map>
#include <queue>
#include <vector>

struct Charger{
    int cnt;
    std::vector<Charger *> next;
    Charger()=default;
    int BFS() const{
        int ans=0;
        std::queue<const Charger *> bfs;
        bfs.push(this);
        while(!bfs.empty()){
            auto cur=bfs.front();
            bfs.pop();
            ans+=cur->cnt;
            for(const auto &nxt:cur->next){
                bfs.push(nxt);
            }
        }
        return ans;
    }
};

int main(){
    int n;
    std::unordered_map<int,Charger *> id;
    Charger *first=new Charger();
    id[1]=first;
    for(int i=2,ui;i<=n;++i){
        std::cin>>ui;
        Charger *cur;
        if(id.contains(i)) cur=id[i];
        else cur=new Charger();
        id[ui]->next.push_back(cur);
    }
    for(int i=1,x;i<=n;++i){
        std::cin>>x;
        id[i]->cnt=x;
    }
}