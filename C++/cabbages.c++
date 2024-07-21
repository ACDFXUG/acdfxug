#include <iostream>

int main(){
    int n,a,x,y;
    scanf("%d%d%d%d",&n,&a,&x,&y);
    printf("%d\n",(n>=a)?a*x+(n-a)*y:n*x);
}