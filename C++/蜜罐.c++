#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

const int MAXN = 1005;
const int MAXM = 100005;

struct Edge {
    int from, to, w;
    bool operator<(const Edge& e) const {
        return w < e.w;
    }
};
Edge edges[MAXM];
int fa[MAXN];

int find(int x) {
    if (x == fa[x]) return x;
    return fa[x] = find(fa[x]);
}

void merge(int x, int y) {
    fa[find(x)] = find(y);
}

int main() {
    int T;
    cin>>T;
    while(T--) {
        int n, m;
        cin >> n >> m;
        for (int i = 1; i <= n; i++) {
            fa[i] = i;
        }
        for (int i = 0; i < m; i++) {
            int u, v, w;
            cin >> u >> v >> w;
            edges[i] = {u, v, w};
        }
        sort(edges, edges + m);
        int cnt = 0;
        vector<Edge> selected;
        for (int i = 0; i < m; i++) {
            int u = edges[i].from, v = edges[i].to, w = edges[i].w;
            if (find(u) != find(v)) {
                merge(u, v);
                cnt++;
                selected.push_back(edges[i]);
                if (cnt == n - 1) break;
            }
        }
        int ans = 0;
        for (auto e : selected) {
            ans += e.w;
        }
        cout << ans << endl;
    }
}