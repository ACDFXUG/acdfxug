#include<iostream>
#include<string>
#include<cmath>
#include<algorithm>
#include<cstring>
#include<cstdio>
#include<queue>
using namespace std;
int u[4][2] = { {1,0},{-1,0},{0,1},{0,-1} };
int vis[5][5];
int s[5][5];
struct node
{
	int x, y;
	node() {}
	node(int x1,int y1):x(x1),y(y1){}
}q[30][30];
void print(int x, int y)
{
	if (x == -1 && y == -1) return;
	print(q[x][y].x, q[x][y].y);
	printf("(%d, %d)\n", x, y);
}
void bfs()
{
	vis[0][0] = 1;
	q[0][0].x = -1;
	q[0][0].y = -1;
	queue<node>Q;
	Q.push(node(0, 0));
	while (!Q.empty()) {
		node t = Q.front();
		Q.pop();
		if (t.x == 4 && t.y == 4) print(4, 4);
		for (int i = 0;i < 4;i++) {
			int xx = t.x + u[i][0];
			int yy = t.y + u[i][1];
			if (xx >= 0 && xx < 5 && yy >= 0 && yy < 5 && (!vis[xx][yy]) && (s[xx][yy] == 0)) {
				vis[xx][yy] = 1;
				Q.push(node(xx, yy));
				q[xx][yy].x = t.x;
				q[xx][yy].y = t.y;
				
			}
		}
	}
 
 
}
int main()
{
	for (int i = 0;i < 5;i++) {
		for (int j = 0;j < 5;j++) {
			scanf("%d", &s[i][j]);
		}
	}
	memset(vis, 0, sizeof(vis));
	bfs();
	return 0;
}