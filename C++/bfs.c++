#include <iostream>
using namespace std;
int n,e,x,y;
int q[15],H,T=1;
int g[15][15],k[15];
void BFS(int n,int e){
    int l,f=1,t;
    for(;H<T;){
        k[q[H]]=1;
        l=H;
        f=0;
        t=q[H];
        cout<<q[H]<<" ";
        H++;
        for(int i=1;i<=n;i++){
            if(k[i]==0&&g[t][i]){
                k[i]=1;
                q[T++]=i;
                f=1;
            }
        }
    }
    return;
}
int main(){
    cin>>n>>e;
    for(int i=0;i<e;i++){
        cin>>x>>y;
        g[x][y]=1;
        g[y][x]=1;
    }
    q[0]=1;
    k[1]=1;
    BFS(n,e);
}
