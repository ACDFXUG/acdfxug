#include <iostream>

int isFound(int A,int B,int C){
    for(int i=A;i<=B;i++){
        if(i%C==0){
            return i;
        }
    }
    return -1;
}

int main(){
    int A,B,C;
    scanf("%d%d%d",&A,&B,&C);
    printf("%d\n",isFound(A,B,C));
}