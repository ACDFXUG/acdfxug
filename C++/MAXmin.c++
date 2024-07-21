#include <iostream>
#include <algorithm>

int main(){
    int N;
    scanf("%d",&N);
    int *xX=new int[N];
    for(int i=0;i<N;scanf("%d",xX+i++));
    std::sort(xX,xX+N);
    int x=xX[0],X=xX[N-1];
    for(;x!=X;){
        for(int i=N-1;i>=0;i--){
            if(xX[i]==X){
                xX[i]-=x;
            }
        }
        std::sort(xX,xX+N);
        x=xX[0];
        X=xX[N-1];
    }
    printf("%d\n",X);
}