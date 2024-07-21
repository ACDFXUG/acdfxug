#include <iostream>

void r_REV(int A[1000][1000],int N,int x){
    for(int i=0;i<N;i++){
        A[x-1][i]^=1;
    }
}

void c_REV(int A[1000][1000],int N,int x){
    for(int i=0;i<N;i++){
        A[i][x-1]^=1;
    }
}

int G(int A[1000][1000],int N){
    int sum=0;
    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            if(A[i][j]==0){
                continue;
            }
            sum+=A[i][j]*A[j][i];
        }
    }
    return sum&1;
}
int P[1000][1000];
int main(){
    int N,Q;
    scanf("%d%d",&N,&Q);
    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            scanf("%d",&P[i][j]);
        }
    }
    for(int s=1;s<=Q;s++){
        int act;
        scanf("%d",&act);
        if(act==1){
            int x;
            scanf("%d",&x);
            r_REV(P,N,x);
        }else if(act==2){
            int x;
            scanf("%d",&x);
            c_REV(P,N,x);
        }else if(act==3){
            printf("%d",G(P,N));
        }
    }
}