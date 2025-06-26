#include <iostream>
#include <unordered_set>
#include <queue>
#include <vector>

template<class U=int ,class V=int >
struct Pair{
    U x;
    V y;
    Pair(const U &x,const V &y):x(x),y(y){}
    Pair operator +(const Pair &rhs) const{
        return {x+rhs.x,y+rhs.y};
    }
    Pair &operator +=(const Pair &rhs){
        x+=rhs.x;
        y+=rhs.y;
        return *this;
    }
    bool operator ==(const Pair &rhs) const{
        if(this==&rhs) return true;
        return x==rhs.x&&y==rhs.y;
    }
};

template<class U=int ,class V=int >
auto pair_hash=[](const Pair<U,V> &p)->size_t {
    return std::hash<U>()(p.x)*31+std::hash<V>()(p.y);
};

const Pair<> horse[8]{
    {2,1},{1,2},{-1,2},{-2,1},
    {-2,-1},{-1,-2},{1,-2},{2,-1}
};

bool is_valid(const int &n,const int &m,const Pair<int,int> &p){
    return p.x>=1&&p.x<=n&&p.y>=1&&p.y<=m;
}

auto min_step(
    const int &n,const int &m,
    const Pair<> &start
){
    std::unordered_set<Pair<>,decltype(pair_hash<>)> visited;
    std::vector<std::vector<int>> chess(n+1,std::vector<int>(m+1,-1));
    std::queue<Pair<>> path;
    chess[start.x][start.y]=0;
    path.push(start);
    visited.insert(start);
    while(!path.empty()){
        const Pair cur=path.front();
        path.pop();
        for(const auto &next:horse){
            const auto nxt=next+cur;
            if(!visited.contains(nxt)&&is_valid(n,m,nxt)){
                path.push(nxt);
                visited.insert(nxt);
                chess[nxt.x][nxt.y]=chess[cur.x][cur.y]+1;
            }
        }
    }
    return chess;
}

int main(){
    int n,m,x,y;
    std::cin>>n>>m>>x>>y;
    auto chess=min_step(n,m,{x,y});
    for(int i=1;i<=n;++i){
        for(int j=1;j<=m;++j){
            std::cout<<chess[i][j]<<(j==m?'\n':' ');
        }
    }
}