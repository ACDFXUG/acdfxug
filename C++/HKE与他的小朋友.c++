// #include <iostream>
// #include <vector>
// #include <unordered_map>
// #include <print>
// int main(){
//     int n,k;
//     std::cin>>n>>k;
//     std::vector<int> arrange(n+1);
//     for(int i=1;i<=n;std::cin>>arrange[i++]);
//     std::vector<int> ans(n+1),tmp(n+1);
//     for(int i=1;i<=n;++i) ans[i]=i;
//     for(int i=1;i<=n;++i){
//         tmp[arrange[i]]=ans[i];
//     }
//     std::unordered_map<int,int> loop_idx;
//     for(int i=1;i<=n;++i){
//         loop_idx[tmp[i]]=i;
//     }
//     std::println("{}",tmp);
//     for(int i=1;i<=n;++i){
//         int idx=loop_idx[ans[i]];
//         int num=tmp[(idx+k+1)%n];
//         i==n?std::println("{}",num):std::print("{} ",num);
//     }
// }
#include <iostream>
#include <vector>
#include <cstring>
#include <print>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, k;
    cin >> n >> k;
    vector<int> arrange(n + 1);
    for (int i = 1; i <= n; ++i) {
        cin >> arrange[i];
    }

    vector<int> ans(n + 1);
    // 初始状态: ans[i] = i
    for (int i = 1; i <= n; ++i) {
        ans[i] = i;
    }

    // 方法：对每个位置 i，找到其在变换下的循环节
    vector<int> result(n + 1);
    vector<bool> visited(n + 1, false);

    for (int start = 1; start <= n; ++start) {
        if (visited[start]) continue;

        // 收集当前循环中的所有点
        vector<int> cycle;
        int cur = start;
        while (!visited[cur]) {
            visited[cur] = true;
            cycle.push_back(cur);
            cur = arrange[cur];
        }

        // 现在 cycle 是一个循环块，长度为 L
        int L = cycle.size();
        int real_k = k % L;  // 实际只需走 real_k 步
        // 对 cycle 中每个位置，计算 k 步后的位置
        for (int i = 0; i < L; ++i) {
            int from = cycle[i];                    // 初始在 from 的元素
            int to_index = (i + real_k) % L;        // k 步后的位置
            int to = cycle[to_index];               // 对应的物理位置
            result[to] = ans[from];                 // 位置 to 上的值是原来 from 上的值
        }
    }

    // 输出结果
    for (int i = 1; i <= n; ++i) {
        cout << result[i] << (i == n ? '\n' : ' ');
    }

    return 0;
}