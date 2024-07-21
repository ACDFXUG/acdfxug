#include<iostream>
#include<queue>
#include<cstring>
#include<cmath>
using namespace std;
int Min(int a,int b){
    return (a+b-fabs(a-b))/2;
}
int inf=1061109567;//表示无穷大。
char graph[202][202];//代表地图。
int dis[2][202][202];//代表yefenfei和 Merceki.到kfc的距离。其中我们设置无穷大为未访问（便于之后的dp）
int go[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
int n,m;//n*m的地图大小。
struct node{
	int x,y;//代表坐标。
}Y,M;//y和m分别代表yefenfei和Merceki的初试位置。
void bfs(node s,int dis[202][202],int n,int m){
	queue<node> q;
	node temp,head;
	dis[s.x][s.y]=0;
	q.push(s);
	for(;q.size()!=0;){
		//我们不用判断退出条件，所做的一切只是为了记录到达kfc的距离。
		head=q.front();
		q.pop();
		for(int i=0;i<4;i++){
			temp.x=head.x+go[i][0];temp.y=head.y+go[i][1];
			if(temp.x>=0&&temp.y>=0&&temp.x<n&&temp.y<m&&graph[temp.x][temp.y]!='#'&&dis[temp.x][temp.y]==inf){
				dis[temp.x][temp.y]=dis[head.x][head.y]+1;
				q.push(temp);
			}
		}
	}
}
int main(){
	for(;cin>>n>>m;){
		int result=inf;
		for(int i=0;i<n;i++){
			cin>>graph[i];
			for(int j=0;j<m;j++){
				//寻找他们的起始位置。
				if(graph[i][j]=='Y'){
					Y.x=i;
                    Y.y=j;
				}
				else if(graph[i][j]=='M'){
					M.x=i;
                    M.y=j;
				}
			}
		}
		memset(dis,inf,sizeof(dis));
		bfs(Y,dis[0],n,m),bfs(M,dis[1],n,m);		
		//已经获得了所有到达kfc的距离。我们遍历求最短时间。
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				if(graph[i][j]=='@'){
					result=Min(result,dis[0][i][j]+dis[1][i][j]);
				}
			}
		}
		cout<<11*result<<endl;
	}
}