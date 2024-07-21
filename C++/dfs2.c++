#include <iostream>
using namespace std;
int s[1000][1000];
int vis[1000][1000];
int fx[4]={1,0,-1,0};
int fy[4]={0,1,0,-1};
int n,e;
void DFS(int x,int y){
    if(vis[x][y]){
        return;
    }
    for(int i=0;i<4;i++){
        int nx=x+fx[i];
        int ny=y+fy[i];
    if(s[x][y]&&1<=nx&&nx<=n&&ny>=1&&ny<=n){
        
    }
    }
}
int main(){
    
    cin>>n>>e;
    for(int a,b;e>0&&cin>>a>>b;e--){
        s[a][b]=1;
        s[b][a]=1;
    }
    DFS(1,1);
}
