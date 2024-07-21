#include <iostream>
using namespace std;
int MATSUM(int A[1000][1000],int N){
    int sum=0;
    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            sum+=A[i][j]*A[j][i];
        }
    }
    return sum;
}
void r_REV(int A[1000][1000],int N,int x){
    for(int j=0;j<N;j++){
        A[x-1][j]=A[x-1][j]==1?0:1;
    }
}
void c_REV(int A[1000][1000],int N,int x){
    for(int i=0;i<N;i++){
        A[i][x-1]=A[i][x-1]==1?0:1;
    }
}
int main(){
    int N,Q;
    scanf("%d%d",&N,&Q);
    int A[1000][1000];
    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            scanf("%d",&A[i][j]);
        }
    }
    for(int i=0;i<Q;i++){
        int ACT;
        scanf("%d",&ACT);
        if(ACT==1){
            int x;
            scanf("%d",&x);
            r_REV(A, N, x);
        }else if(ACT==2){
            int x;
            scanf("%d",&x);
            c_REV(A, N, x);
        }else if(ACT==3){
            printf("%d",MATSUM(A, N)%2);
        }
    }
}