#include <iostream>
using namespace std;
int n,m,num;
char a[400][400];
void dfs(int x,int y,int n,int m,int num)
{
    if(x>n||x<1||y>m||y<1||a[x][y]==48)
    {
        return;
    }
    a[x][y]=48;
    dfs(x+1,y,n,m,num);
    dfs(x,y+1,n,m,num);
    dfs(x-1,y,n,m,num);
    dfs(x,y-1,n,m,num);
}
int main()
{
    cin>>n>>m;
    for(int i=1;i<=n;i++)
    {
        for(int j=1;j<=m;j++) {
            cin>>a[i][j];
        }
    }
    for(int i=1;i<=n;i++)
    {
        for(int j=1;j<=m;j++)
        {
            if(a[i][j]!=48){
                num++;
                dfs(i,j,n,m,num);
            }
        }
    }
    cout<<num<<endl;
}
