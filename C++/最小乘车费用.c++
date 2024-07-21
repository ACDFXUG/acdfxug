#include <iostream>
#include <cstring>
using namespace std;
int INF=0x3f3f3f3f,p[1000],a[10],m;
int minimum(int x,int y){
    return (x<y)?x:y;
}
int main(){
    memset(p,INF,sizeof(p));
    p[0]=0;
    for(int i=0;i<10;i++){
        cin>>a[i];
    }
    cin>>m;
    for(int i=0;i<10;i++){
        for(int j=i;j<=m;j++){
            p[j]=minimum(p[j],p[j-i-1]+a[i]);
        }
    }
    cout<<p[m]<<endl;
}