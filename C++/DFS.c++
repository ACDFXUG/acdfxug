#include <iostream>
#include <cstring>
using namespace std;
int s[1000][1000],a[1000],x[1000];
int r,t,j=0;
void DFS(int t,int r,int x[1000]){
    a[j++]=t;
    x[t]=1;
    for(int i=1;i<=r;i++){
        if(s[t][i]==1&&x[i]!=1){
            DFS(i,r,x);
        }
    }
}
int main(){
    for(int m,n;cin>>m>>n;){
        memset(x,0,sizeof(x));
        int y,z;
        for(int i=0;i<n;i++){
            cin>>y>>z;
            s[y][z]=1;
            s[z][y]=1;
        }
        DFS(1,m,x);
        for(int i=0;i<m-1;i++){
            cout<<a[i]<<" ";          
        }
 cout<<a[m-1]<<endl;
    }
}
