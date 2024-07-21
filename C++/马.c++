#include<iostream>
#include<queue>
#include<cstring>
using namespace std;
queue<pair<int,int> >q;//马能走到的棋盘上的坐标 

bool vis[500][500];//表示是否已经访问过 
int n,m,sx,sy,len[500][500];//表示马到达该坐标处的最少步数
int dis[8][2]={1,2,2,1,1,-2,2,-1,-1,2,-2,1,-1,-2,-2,-1};//马可以走的八个方向 


void bfs(int n,int m,int sx,int sy){
	q.push(make_pair(sx,sy));//入队
	vis[sx][sy]=1;//标记已经访问过 
	for(;q.size()!=0;){	
		//取队首元素
		int x=q.front().first;
		int y=q.front().second; 
		q.pop();//出队 	
		for(int i=0;i<8;i++){//遍历可走的方向 
			int dx=x+dis[i][0];
			int dy=y+dis[i][1];
			if(dx>=1&&dx<=n&&dy>=1&&dy<=m&&vis[dx][dy]==0){//可达点 
				vis[dx][dy]=1;//标记已访问过
				len[dx][dy]=len[x][y]+1;//距离加1
				q.push(make_pair(dx,dy)); //以该点起点 				
			}
		}	
	}
	for(int i=1;i<=n;i++){
		for(int j=1;j<=m;j++){	
			cout<<len[i][j];
			if(j<m){
				cout<<" ";
			}
		}
		cout<<endl;	
	}	
}
int main(){
	cin>>n>>m>>sx>>sy;//输入棋盘大小，马的初始位置
	memset(len,-1,sizeof(len));//初始距离为-1，表示不能到达
	len[sx][sy]=0;//起点到起点距离为0	
	//bfs求解马到棋盘上任意一点的最少步数 
	bfs(n,m,sx,sy); 	
}