#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
using namespace std;

const int MAXN = 1005;
const int INF = 0x3f3f3f3f;
int n, m, s, t;
struct Edge {
    int to, w;
    Edge(int t, int _w) : to(t), w(_w) {}
};

vector<Edge> G[MAXN];
int dis[MAXN];
int vis[MAXN];

void dijkstra(int s) {
    memset(dis, INF, sizeof(dis));
    memset(vis, 0, sizeof(vis));
    dis[s] = 0;
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push(make_pair(0, s));
    for(;!pq.empty();) {
        pair<int, int> p = pq.top(); pq.pop();
        int v = p.second;
        if (vis[v]) continue;
        vis[v] = 1;
        for (int i = 0; i < G[v].size(); i++) {
            Edge e = G[v][i];
            if (dis[e.to] > dis[v] + e.w) {
                dis[e.to] = dis[v] + e.w;
                pq.push(make_pair(dis[e.to], e.to));
            }
        }
    }
}

int main() {    
    cin >> n >> m >> s >> t;
    for (int i = 0; i < m; i++) {
        int a, b, v;
        cin >> a >> b >> v;
        G[a].push_back(Edge(b, v));
        G[b].push_back(Edge(a, v));
    }
    dijkstra(s);
    if (dis[t] == INF) cout << "-1" << endl;
    else cout << dis[t] << endl;
    return 0;
}