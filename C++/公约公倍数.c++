#include <iostream>
using namespace std;

int gcd(int P,int Q){
    return Q>0?gcd(Q,P%Q):P;
}
int lcm(int P,int Q){
    return P*Q/gcd(P,Q);
}
int main(){
    int x,y,t=0;
    scanf("%d%d",&x,&y);
    for(int P=1;P<=x*y;P++){
        for(int Q=P;Q<=x*y;Q++){
            if(gcd(P, Q)==x&&lcm(P, Q)==y){
                t++;
            }
        }
    }
    printf("%d",2*t);
}