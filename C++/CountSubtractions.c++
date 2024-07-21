#include <iostream>
typedef unsigned long long Ull;

int main(){
    Ull a,b,t;
    scanf("%lld%lld",&a,&b);
    for(t=0;a!=b;t++){
        (a>b)?a-=b:b-=a;
    }
    printf("%d\n",t);
}