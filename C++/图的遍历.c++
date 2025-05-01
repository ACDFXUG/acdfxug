// #include <iostream>
// #include <queue>
// #include <unordered_map>
// #include <unordered_set>
// #include <cmath>
// #include <vector>

// template<class K,class V>
// using hashmap=std::unordered_map<K,V>;

// template<class T>
// using hashset=std::unordered_set<T>;

// struct Graph{
// private:
//     hashmap<int,hashset<int>> adj;
// public:
//     Graph()=default;
//     void add_edge(int src,int dst){
//         adj[src].insert(dst);
//     }
//     Graph &operator ()(const int &src,const int &dst){
//         add_edge(src,dst);
//         return *this;
//     }
//     auto &operator [](const int &src){
//         return adj[src];
//     }
// };

// static hashmap<int,int> memory;
// //求bfs路径中的最大编号
// int bfsmax(Graph &g,int src){
//     if(memory.contains(src)){
//         return memory[src];
//     }
//     hashset<int> visited;
//     std::queue<int> q;
//     q.push(src);
//     visited.insert(src);
//     int max=src;
//     for(int cur;!q.empty();max=std::max(max,cur)){
//         cur=q.front();
//         q.pop();
//         if(memory.contains(cur)){
//             max=std::max(max,memory[cur]);
//             continue;
//         }
//         for(const int &dst:g[cur]){
//             if(!visited.contains(dst)){
//                 visited.insert(dst);
//                 q.push(dst);
//             }
//         }
//     }
//     return memory[src]=max;
// }

// int main(){
//     int n/*顶点数*/,m/*边数*/;
//     std::cin>>n>>m;
//     Graph grh;
//     for(int s,d,i=0;i<m;++i){
//         std::cin>>s>>d;
//         grh(s,d);
//     }
//     std::vector<int> ans(n);
//     for(int i=n;i>0;--i){
//         ans[i-1]=bfsmax(grh,i);
//     }
//     for(int i=0;i<n;++i){
//         printf(i==n-1?"%d\n":"%d ",ans[i]);
//     }
// }

#include <iostream>
#include <vector>
#include <queue>
#include <unordered_set>

using namespace std;

int main() {
    int n, m;
    cin >> n >> m;

    // 原图：u -> v
    vector<vector<int>> graph(n + 1);
    // 反向图：v -> u
    vector<vector<int>> reverse_graph(n + 1);

    for (int i = 0; i < m; ++i) {
        int u, v;
        cin >> u >> v;
        graph[u].push_back(v);
        reverse_graph[v].push_back(u);
    }

    // ans[i] 表示从i出发能到达的最大编号
    vector<int> ans(n + 1, 0);

    // 按照最大编号开始 BFS/DFS，更新所有能到达它的节点
    vector<bool> visited(n + 1, false);
    for (int max_node = n; max_node >= 1; --max_node) {
        if (!visited[max_node]) {
            queue<int> q;
            q.push(max_node);
            visited[max_node] = true;

            while (!q.empty()) {
                int u = q.front();
                q.pop();

                ans[u] = max(ans[u], max_node); // 更新答案

                for (const int &v : reverse_graph[u]) {
                    if (!visited[v]) {
                        visited[v] = true;
                        q.push(v);
                    }
                }
            }
        }
    }

    for (int i = 1; i <= n; ++i) {
        cout << ans[i] << " ";
    }
    cout << endl;

    return 0;
}