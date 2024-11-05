#include <iostream>
#include <algorithm>
#include <cmath>

int main(){
    int N,sum=0;
    scanf("%d",&N);
    int *X=new int[N];
    for(int i=0;i<N;sum+=i++[X]){
        scanf("%d",X+i++);
    }
    int P=std::round(sum*1./N),cost=0;
    for(int i=0;i<N;i++){
        cost+=(X[i]-P)*(X[i]-P);
    }
    printf("%d\n",cost);
    delete[] X;
}