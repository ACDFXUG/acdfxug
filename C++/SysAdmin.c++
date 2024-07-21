#include <iostream>

struct server{
    int x=0,y=0,server=0;
}a,b;


int main(){
    int n;
    scanf("%d",&n);
    for(int t,x,y;n--;){
        scanf("%d%d%d",&t,&x,&y);
        if(t==1){
            a.server++;
            a.x+=x;
            a.y+=y;
        }else if(t==2){
            b.server++;
            b.x+=x;
            b.y+=y;
        }
    }
    double A=10*a.server,B=10*b.server;
    printf(a.x/A>=0.5?"LIVE\n":"DEAD\n");
    printf(b.x/B>=0.5?"LIVE\n":"DEAD\n");
}