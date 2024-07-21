#include <iostream>
#include <algorithm>
#include <cstring>
#include <queue>
using namespace std;
string s[1000];
int d[4][2]={0,1,0,-1,1,0,-1,0};
int v[1000][1000],f[1000][1000],b[1000][2];
int r,n;
void dfs(int x,int y,int v[1000][1000],int b[1000][2]){
    r++;
    b[r][0]=x,b[r][1]=y;
    for(int i=0;i<4;i++){
        int X=x+d[i][0],Y=y+d[i][1];
        if(X>=0&&X<n&&Y>=0&&Y<n&&v[X][Y]&&s[X][Y]!=s[x][y]){
            v[X][Y]=0;
            dfs(X,Y,v,b);
        }
    }
}
int main(){
    int x,y,m;
    cin>>n>>m;
    for(int i=0;i<n;i++){
        cin>>s[i];
    }
    memset(v,1,sizeof(v));
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            if(v[i][j]){
                v[i][j]=!v[i][j];
                r=0;
                dfs(i,j,v,b);
                for(int k=1;k<=r;k++){
                    f[b[k][0]][b[k][1]]=r;
                }
            }
            
        }
    }
    for(;m>0&&cin>>x>>y;m--){
        cout<<f[x-1][y-1]<<endl;
    }
}

