#include <iostream>
#include <vector>
#include <queue>
using namespace std;

struct G{
    int v;
    vector<int> *ADJ;
    G(int v){
        this->v=v;
        ADJ=new vector<int>[v];
    }
};
void ADDEDGE(G &g, int u,int v){
    g.ADJ[u].push_back(v);
    g.ADJ[v].push_back(u);
}
void PRINT(G &g){
    for(int i=0;i<g.v;i++){
        cout<<"point ";
        cout<<char(i+'A')<<endl;
        cout<<"head";
        for(auto x:g.ADJ[i]){
            cout<<"->"<<char(x+'A');
        }
        cout<<endl;
    }
}
void dfs(int v, vector<int> &vis, G &g){
    vis[v]=1;
    cout<<char(v+'A')<<" ";
    for(int i=0;i<g.ADJ[v].size();i++){
        if(!vis[g.ADJ[v][i]]){
            dfs(g.ADJ[v][i],vis,g);
        }
    }
}
void DFS(G &g){
    vector<int> vis(g.v,0);
    for(int i=0;i<g.v;i++){
        if(!vis[i]){
            dfs(i,vis,g);
        }
    }
}
void BFS(int p, G &g){
    vector<int> vis(g.v,0);
    queue<int> q;
    vis[p]=1;
    q.push(p);
    for(;!q.empty();){
        p=q.front();
        cout<<char(p+'A')<<" ";
        q.pop();
        for(int i=0;i<g.ADJ[p].size();i++){
            if(!vis[g.ADJ[p][i]]){
                vis[g.ADJ[p][i]]=1;
                q.push(g.ADJ[p][i]);
            }
        }
    }
}
int main(){
    int v;
    cout<<"input numbers of point:"<<endl;
    cin>>v;
    G g(v);
    for(int i,j,p=0;p<=v&&cin>>i>>j;p++){
        ADDEDGE(g,i,j);
    }
    PRINT(g);
    cout<<"DFS:"<<endl;
    DFS(g);
    cout<<"\nBFS:"<<endl;
    BFS(0,g);
}