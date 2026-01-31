#include <iostream>
#include <unordered_set>
#include <stack>
#include <vector>

template<class K>
using hashset=std::unordered_set<K>;

const int dir[4][2]{
    {0,1},{0,-1},
    {1,0},{-1,0}
};

int max_step=0;

void backtrack(hashset<char> &visited,int x,int y,int step,
const std::vector<std::vector<char>> &maze,int n,int m){
    if(x<0||x>=n||y<0||y>=m||visited.count(maze[x][y])){
        return;
    }
    visited.insert(maze[x][y]); 
    max_step=std::max(max_step,step);
    for(int i=0;i<4;++i){
        int nx=x+dir[i][0];
        int ny=y+dir[i][1];
        backtrack(visited,nx,ny,step+1,maze,n,m);
    }
    visited.erase(maze[x][y]);
}

int main(){
    int n,m;
    std::cin>>n>>m;
    std::vector<std::vector<char>> maze(n,std::vector<char>(m));
    for(int i=0;i<n;++i){
        for(int j=0;j<m;++j){
            std::cin>>maze[i][j];
        }
    }
    hashset<char> visited;
    backtrack(visited,0,0,1,maze,n,m);
    std::cout<<max_step<<std::endl;
}

// 全局 [visited] 变量在 BFS 和 DFS 中的适用情况有明确的区别：

// ## 适用于全局 [visited] 的情况：

// ### 1. **单路径搜索问题**
// - **连通性检测**：判断图中两点是否连通
// - **简单可达性**：只要知道能否到达目标即可
// - **遍历整个连通分量**：统计连通块大小等

// ### 2. **最短路径问题（BFS）**
// ```cpp
// // 典型的BFS最短路径
// bool visited[MAX_N][MAX_M];
// queue<Pos> q;
// q.push(start);
// visited[start.x][start.y] = true;

// while(!q.empty()) {
//     Pos cur = q.front(); q.pop();
//     // 处理当前节点...
//     for(auto& dir : directions) {
//         int nx = cur.x + dir[0], ny = cur.y + dir[1];
//         if(!visited[nx][ny] && valid(nx, ny)) {
//             visited[nx][ny] = true;
//             q.push({nx, ny});
//         }
//     }
// }
// ```

// ## 不适用于全局 [visited] 的情况：

// ### 1. **路径计数/最优路径问题**
// - ✅ **你的问题**：寻找经过不同字符的最大步数
// - 寻找最长路径
// - 需要探索所有可能路径的问题

// ### 2. **回溯算法**
// - **N皇后问题**
// - **数独求解**
// - **排列组合生成**

// ## 两种策略对比：

// | 算法类型 | 全局visited | 局部visited(回溯) |
// |---------|-------------|------------------|
// | BFS最短路 | ✅ 适用 | ❌ 不适用 |
// | DFS连通性 | ✅ 适用 | ❌ 不适用 |
// | 路径优化 | ❌ 不适用 | ✅ 适用 |
// | 回溯搜索 | ❌ 不适用 | ✅ 适用 |

// ## 你的代码为什么需要局部 [visited]：

// ```cpp
// // 你的代码：需要探索所有可能路径
// visited.insert(maze[x][y]);      // 访问当前字符
// // ... 递归探索 ...
// visited.erase(maze[x][y]);       // 回溯，恢复状态
// ```

// **原因**：同一个字符在不同的路径组合中可能是可访问的，全局 [visited] 会过早地排除有效路径。

// **总结**：全局 [visited]适用于"临时访问，探索后恢复"的场景。