#include <iostream>
#include <string.h>
#include <stdio.h>
using namespace std;
int s[1000][1000];//邻接矩阵 
int a[1000];//将dfs遍历序列储存在里面 
int x[1000];//相当于visited数组 
int j=0;
 
void dfs(int t, int n) 
{
	a[j++] = t;
	x[t] = 1;
	for (int i = 1; i <= n; i++) {
		if (s[t][i] == 1 && x[i] != 1)
			dfs(i, n);
	}
}
 
int main() {
	int m, n;
	while (cin >> m >> n) {
		memset(x, 0, sizeof(x));
		int y, z;
		for (int i = 0; i < n; i++) {
			cin >> y >> z;
			s[y][z] = s[z][y] = 1;//输入边
		}
		
		dfs(1, m);
		for (int i = 0; i < m - 1; i++)
			cout << a[i] << " ";
		cout << a[m - 1] << endl;
	}
 
	return 0;
}