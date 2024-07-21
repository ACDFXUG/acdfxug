#include <iostream>
#include <vector>
#include <queue>
#include <cstring>

using namespace std;

const int INF = 0x3f3f3f3f;
const int MAXN = 1005;

int order, K, M, S, T;
int C[MAXN]; // 存储每个国家的文化
int G[MAXN][MAXN]; // 存储文化排斥关系
vector<pair<int, int>> edges[MAXN]; // 存储道路信息

int check(int u, int v, int c) {
    // 判断国家v的文化c是否被国家u排斥
    if (G[C[u]][c]) return 0;
    // 判断国家u的文化c是否被国家v排斥
    if (G[c][C[v]]) return 0;
    return 1;
}

int dijkstra(int s, int t) {
    int dist[MAXN];
    memset(dist, INF, sizeof(dist));
    dist[s] = 0;
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push({0, s});
    while (!pq.empty()) {
        int u = pq.top().second;
        int d = pq.top().first;
        pq.pop();
        if (u == t) return d;
        if (dist[u] < d) continue;
        for (auto e : edges[u]) {
            int v = e.first;
            int w = e.second;
            for (int c = 1; c <= K; c++) {
                if (check(u, v, c)) {
                    if (dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                        pq.push({dist[v], v});
                    }
                    break;
                }
            }
        }
    }
    return -1; // 无解
}

int main() {
    for (;cin >> order >> K >> M >> S >> T;) {
        for (int i = 1; i <= order; i++) {
            cin >> C[i];
        }
        memset(G, 0, sizeof(G));
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= K; j++) {
                cin >> G[i][j];
            }
        }
        for (int i = 1; i <= M; i++) {
            int u, v, d;
            cin >> u >> v >> d;
            edges[u].push_back({v, d});
            edges[v].push_back({u, d});
        }
        int ans = dijkstra(S, T);
        cout << ans << endl;
    }
}