#include<iostream>
#include<cstring>
#include<algorithm>
#include<vector>

using namespace std;

vector<vector<int> >G;
int vis[1001];
int n,s = 0,m;
int value[1001];
int indexx;

int dfs(int child,int parent)
{
    vis[child] = -1;
    vector<int>::iterator it;
    for(it = G[child].begin(); it != G[child].end(); ++it)
    {
        indexx = *it;
        if(vis[indexx] == -1)
            return 0;
        else
        {
            if(!vis[indexx])
            {
                if(!dfs(indexx,child))
                    return 0;
            }
            else//拓扑排序的灵活处理，<更新>父节点的值
            {
                if(value[indexx]+1 > value[child])
                    value[child] = value[indexx]+1;
            }
        }
    }

    vis[child] = 1;
    s += 100 + value[child];
    if(parent != 0 && value[child]+1 > value[parent])
        value[parent] = value[child] + 1;//思路要严密，更新父节点的值
    return 1;
}

int topst(int n)
{
    for(int i = 1; i <= n; ++i)
    {
        if(!vis[i] && !dfs(i,0))
           return 0;
    }
    return 1;
}

int main()
{
    G.clear();
    memset(vis,0,sizeof(vis));
    memset(value,0,sizeof(value));
    cin>>n>>m;
    G.resize(n+1);
    int a,b;
    for(int i=1; i<=m&&cin>>a>>b; ++i)
    {
        
        G[a].push_back(b);
    }
    if(topst(n))
    {
        cout<<s<<endl;
    }
    else{
        cout<<"impossible"<<endl;
    }
}
