#include <iostream>

int main(){
    int N,score;
    for(scanf("%d",&N),score=0;N>1;score++){
        if(N%2==1&&N!=1){
            N*=3;
            N++;
        }else if(N%2==0){
            N/=2;
        }
    }
    printf("%d",score);
}