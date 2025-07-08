#include <iostream>
#include <vector>
#include <unordered_set>
#include <queue>

int M,N;
std::vector<std::vector<int>> grid;
std::vector<std::vector<int>> ans;

template<class I=int ,class J=I >
auto pair_hash=[](const std::pair<I,J> &p){
    return std::hash<I>()(p.first)*31+std::hash<J>()(p.second);
};

void bfs(int sx,int sy,const std::vector<std::pair<int,int>> &dirs){
    std::unordered_set<std::pair<int,int>,decltype(pair_hash<>)> vis;
    std::queue<std::pair<int,int>> bfs;
    bfs.emplace(sx,sy);
    vis.emplace(sx,sy);
    while(!bfs.empty()){
        auto [x,y]=bfs.front();
        bfs.pop();
        for(const auto &[dx,dy]:dirs){
            int nx=x+dx,ny=y+dy;
            if(nx>=0&&nx<M&&ny>=0&&ny<N){
                if(std::pair nxt(nx,ny);!vis.contains(nxt)
                    &&(grid[nx][ny]==1||grid[nx][ny]==4||grid[nx][ny]==3)
                ){
                    ans[nx][ny]=ans[x][y]+1;
                    bfs.push(nxt);
                    vis.insert(nxt);
                }
            }
        }
    }
}

int main(){
    int M1,M2;
    std::cin>>M>>N>>M1>>M2;
    grid.resize(M,std::vector<int>(N,0));
    ans.resize(M,std::vector<int>(N,0));
    const std::vector<std::pair<int,int>> dirct{
        {M1,M2},{M1,-M2},{-M1,M2},{-M1,-M2},
        {M2,M1},{M2,-M1},{-M2,M1},{-M2,-M1}
    };
    int sx,sy,ex,ey;
    for(int i=0;i<M;++i){
        for(int j=0;j<N;++j){
            std::cin>>grid[i][j];
            if(grid[i][j]==3){
                sx=i;sy=j;
            }else if(grid[i][j]==4){
                ex=i;ey=j;
            }
        }
    }
    bfs(sx,sy,dirct);
    std::cout<<ans[ex][ey]<<std::endl;
}