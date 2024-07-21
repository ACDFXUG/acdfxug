#include <iostream>

int main(){
    int N,*A=new int[100001](),t=0;
    scanf("%d",&N);
    for(int x;N--;A[x]++){
        scanf("%d",&x);
    }
    for(int i=0;i<=100000;i++){
        if(A[i]>=2){
            t+=A[i]-1;
        }
    }
    printf("%d\n",t);
    delete[] A;
}