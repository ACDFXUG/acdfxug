#include <bits/stdc++.h>
using namespace std;

const int MAXN = 5000; // 最多有 5000 个点

int n; // 点的数量
pair<int, int> points[MAXN]; // 存储每个点的坐标

int main() {
    cin >> n; // 输入点的数量

    for (int i = 0; i < n; i++) { // 遍历每个点
        cin >> points[i].first >> points[i].second; // 输入点的坐标
        points[i].second = i; // 存储纵坐标和点的编号
    }

    sort(points, points + n); // 按照纵坐标排序

    set<int> s; // 存储纵坐标
    int ans = 0; // 存储答案

    // 遍历每个点
    for (int i = 0; i < n; i++) {
        // 找到最左边和最右边的点
        auto ia = s.lower_bound(points[i].first);
        auto ib = s.upper_bound(points[i].first);

        if (ia != s.begin()) { // 如果存在最左边的点
            // 计算出答案
            ans += points[i].first - *prev(ia) - 1;
        }
        if (ib != s.end()) { // 如果存在最右边的点
            // 计算出答案
            ans += *ib - points[i].first - 1;
        }

        s.insert(points[i].first); // 插入纵坐标
    }

    cout << ans << endl; // 输出答案

    return 0;
}