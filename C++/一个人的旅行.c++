#include <iostream>
#include <queue>
#include <vector>
#include <cstring>
using namespace std;

const int INF = 0x3f3f3f3f;
const int MAXN = 1010;

struct Edge {
    int to, cost;
    Edge(int _to, int _cost) {
        to = _to;
        cost = _cost;
    }
};

vector<Edge> G[MAXN];
int d[MAXN];
int used[MAXN];

void dijkstra(int s,vector<Edge> G[MAXN]) {
    memset(d, INF, sizeof(d));
    memset(used, false, sizeof(used));
    d[s] = 0;
    priority_queue<pair<int, int>, vector<pair<int, int> >, greater<pair<int, int> > > q;
    q.push(make_pair(0, s));
    while (!q.empty()) {
        int v = q.top().second;
        q.pop();
        if (used[v]) {
            continue;
        }
        used[v] = 1;
        for (int i = 0; i < G[v].size(); i++) {
            int to = G[v][i].to;
            int cost = G[v][i].cost;
            if (d[to] > d[v] + cost) {
                d[to] = d[v] + cost;
                q.push(make_pair(d[to], to));
            }
        }
    }
}

int main() {
    int T, S, D;
    while (cin >> T >> S >> D) {
        memset(G, 0, sizeof(G));
        for (int i = 0; i < T; i++) {
            int a, b, time;
            cin >> a >> b >> time;
            G[a].push_back(Edge(b, time));
            G[b].push_back(Edge(a, time));
        }
        for (int i = 0; i < S; i++) {
            int s;
            cin >> s;
            G[0].push_back(Edge(s, 0));
            G[s].push_back(Edge(0, 0));
        }
        for (int i = 0; i < D; i++) {
            int d;
            cin >> d;
            G[d].push_back(Edge(MAXN - 1, 0));
            G[MAXN - 1].push_back(Edge(d, 0));
        }
        dijkstra(0,G);
        cout << d[MAXN - 1] << endl;
    }
}