#include <iostream>
using namespace std;

const int INF = 0x3f3f3f3f; // INF表示正无穷
int n; // n个顶点

void floyd(int** graph) {
    // 初始化，将graph中为0的边权重设置为INF
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (graph[i][j] == 0) {
                graph[i][j] = INF;
            }
        }
    }
    // Floyd算法核心代码
    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] > graph[i][k] + graph[k][j]) {
                    graph[i][j] = graph[i][k] + graph[k][j];
                }
            }
        }
    }
}

int main() {
    cin >> n;
    int** graph = new int*[n]; // 二维数组，存储图的邻接矩阵
    for (int i = 0; i < n; i++) {
        graph[i] = new int[n];
        for (int j = 0; j < n; j++) {
            cin >> graph[i][j];
        }
    }
    floyd(graph);
    // 输出结果
    for(int i=0;i<n;i++){
        graph[i][i]=0;
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (graph[i][j] == INF) {
                cout << -1 << " ";
            } else {
                cout << graph[i][j] << " ";
            }
        }
        cout << endl;
    }
}