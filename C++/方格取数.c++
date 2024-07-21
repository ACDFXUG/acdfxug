#include <iostream>
using namespace std;
int a[1000][1000],p[1000][1000];
int maximum(int a,int b){
    return (a>b)?a:b;
}
int f(int x,int y,int p[1000][1000]){
    if(p[x][y]!=-1){
        return p[x][y];
    }
    if(x==0&&y==0){
        return a[x][y];
    }else if(x==0&&y>0){
        if(p[x][y-1]==-1){
            p[x][y-1]=f(x,y-1,p);
        }
        return f(x,y-1,p)+a[x][y];
    }else if(x>0&&y==0){
        if(p[x-1][y]==-1){
            p[x-1][y]=f(x-1,y,p);
        }
        return f(x-1,y,p)+a[x][y];
    }else{
        if(p[x-1][y]==-1){
            p[x-1][y]=f(x-1,y,p);
        }
        if(p[x][y-1]==-1){
            p[x][y-1]=f(x,y-1,p);
        }
        return maximum(f(x-1,y,p),f(x,y-1,p))+a[x][y];
    }
    
}
int main(){
    int n;
    cin>>n;
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            p[i][j]=-1;
            cin>>a[i][j];
        }
    }
    cout<<f(n-1,n-1,p);
}