#include <iostream>
#include <vector>
#include <queue>

int main(){
    int N,M;
    std::cin>>N>>M;
    std::vector<std::vector<int>> image(N,std::vector<int>(M));
    std::vector<std::vector<int>> dist(N,std::vector<int>(M,INT_MAX));
    for(int i=0;i<N;++i){
        std::string p;
        std::cin>>p;
        for(int j=0;j<M;++j){
            image[i][j]=p[j]-'0';
        }
    }
    std::queue<std::pair<int,int>> q;
    for(int i=0;i<N;++i){
        for(int j=0;j<M;++j){
            if(image[i][j]==1){
                dist[i][j]=0;
                q.push({i,j});
            }
        }
    }
    const std::pair<int,int> dir[4]{
        {0,1},{0,-1},
        {1,0},{-1,0}
    };
    while(!q.empty()){
        const auto &[x,y]=q.front();
        q.pop();
        for(int nx,ny;const auto &[dx,dy]:dir){
            nx=x+dx,ny=y+dy;
            if(nx>=0&&nx<N&&ny>=0&&ny<M&&dist[nx][ny]>dist[x][y]+1){
                dist[nx][ny]=dist[x][y]+1;
                q.push({nx,ny});
            }
        }
    }
    for(const auto &row:dist){
        for(int j=0;j<M;++j){
            printf(j==M-1?"%d\n":"%d ",row[j]);
        }
    }
}