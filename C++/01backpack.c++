#include <iostream>
using namespace std;
int a[99999][2];
int maximum(int p,int q){
    return (p>q)?p:q;
}
int f(int x,int y,int a[99999][2]){
    if(x==0){
        return x;
    }
    if(a[x][0]>y){
        return f(x-1,y,a);
    }else{
        return maximum(f(x-1,y,a),f(x-1,y-a[x][0],a)+a[x][1]);
    }
}
int main(){
    int m,n;
    cin>>m>>n;
    for(int i=1;i<=n;i++){
        cin>>a[i][0]>>a[i][1];
    }
    cout<<f(n,m,a)<<endl;
}
