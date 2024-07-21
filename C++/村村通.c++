#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
using namespace std;

const int order = 10010;

int n, m;
vector<int> g[order];
bool vis[order];

void bfs(int u)
{
    queue<int> q;
    q.push(u);
    vis[u] = true;
    while (!q.empty())
    {
        int t = q.front();
        q.pop();
        for (int i = 0; i < g[t].size(); i++)
        {
            int v = g[t][i];
            if (!vis[v])
            {
                q.push(v);
                vis[v] = true;
            }
        }
    }
}

int main()
{
    while (cin >> n >> m)
    {
        memset(vis, false, sizeof(vis));
        for (int i = 1; i <= n; i++)
            g[i].clear();
        for (int i = 0; i < m; i++)
        {
            int u, v;
            cin >> u >> v;
            g[u].push_back(v);
            g[v].push_back(u);
        }
        int cnt = 0;
        for (int i = 1; i <= n; i++)
        {
            if (!vis[i])
            {
                bfs(i);
                cnt++;
            }
        }
        cout << cnt - 1 << endl;
    }
}