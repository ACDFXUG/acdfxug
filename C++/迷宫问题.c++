#include <iostream>
#include <cstring>
#include <cmath>
#include <algorithm>
#include <queue>
using namespace std;
int u[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
int v[5][5],s[5][5];
struct node
{
    int x,y;
    node(){}
    node(int x1,int y1):x(x1),y(y1){}
}q[30][30];
 void out(int x,int y){
    if(x==-1&&y==-1){
        return;
    }
    out(q[x][y].x,q[x][y].y);
    cout<<"("<<x<<", "<<y<<")"<<endl;
 }
void bfs(int v[5][5],int s[5][5])
{
    v[0][0]=1;
    q[0][0].x=-1;
    q[0][0].y=-1;
    queue<node> Q;
    Q.push(node(0,0));
    for(;Q.size()!=0;){
        node t=Q.front();
        Q.pop();
        if(t.x==4&&t.y==4){
            out(4,4);
        }
        for(int i=0;i<4;i++){
            int X=t.x+u[i][0],Y=t.y+u[i][1];
            if (X >= 0 && X < 5 && Y >= 0 && Y < 5 && v[X][Y]==0&&s[X][Y] == 0) {
				v[X][Y] = 1;
				Q.push(node(X, Y));
				q[X][Y].x = t.x;
				q[X][Y].y = t.y;
				
			}
        }
    }
}
int main(){
    for(int i=0;i<5;i++){
        for(int j=0;j<5;j++){
            cin>>s[i][j];
        }
    }
    memset(v,0,sizeof(v));
    bfs(v,s);
}
