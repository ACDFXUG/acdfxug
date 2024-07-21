#include <iostream>
#include <algorithm>
#include <cstring>
using namespace std;
int m,n;
char s[10][10];
int p[10];
long long total,con;
void DFS(int cur){
    if(con==m){
        total++;
        return;
    }
    if(cur>=n){
        return;
    }
    for(int j=0;j<n;j++){
        if(p[j]==0&&s[cur][j]=='#'){
            p[j]=1,con++;        
            DFS(cur+1);
            p[j]=0,con--;
        }
    }
    DFS(cur+1);
}
int main(){
    for(;cin>>n>>m&&n!=-1&&m!=-1;){
        total=0,con=0;
        
        memset(p,0,sizeof(p));
        for(int i=0;i<n;i++){
            cin>>s[i];
        }
        DFS(0);
        cout<<total<<endl;
    }
}