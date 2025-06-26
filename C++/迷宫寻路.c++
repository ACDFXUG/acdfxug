#include <iostream>
#include <unordered_set>
#include <vector>
#include <stack>

template<class ...T>
std::istream &std_in(T &...args){
    return (std::cin>>...>>args);
}

auto pair_hash=[](const std::pair<int,int> &p){
    return std::hash<int>()(p.first)*31+std::hash<int>()(p.second);
};

const int dx[]{1,0,-1,0},dy[]{0,1,0,-1};

int main(){
    int n,m;
    std_in(n,m);
    std::vector<std::string> maze(n);
    for(int i=0;i<n;std_in(maze[i++]));
    std::unordered_set<std::pair<int,int>,decltype(pair_hash)> visited;
    std::stack<std::pair<int,int>> path;
    path.emplace(0,0);
    visited.emplace(0,0);
    while(!path.empty()){
        const auto &[x,y]=path.top();
        path.pop();
        if(x==n-1&&y==m-1){
            std::cout<<"Yes"<<std::endl;
            return 0;
        }
        for(int i=0;i<4;++i){
            int nx=x+dx[i],ny=y+dy[i];
            if(nx>=0&&nx<n&&ny>=0
                &&ny<m&&maze[nx][ny]=='.'
                &&!visited.contains({nx,ny})){
                path.emplace(nx,ny);
                visited.emplace(nx,ny);
            }
        }
    }
    std::cout<<"No"<<std::endl;
}