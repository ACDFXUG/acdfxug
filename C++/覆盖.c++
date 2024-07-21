#include <iostream>
int GROUND[5000][5000]={0};
int main(){
    int N,M,B,G,t=0;
    scanf("%d%d%d%d",&N,&M,&B,&G);
    for(int s=1;s<=B;s++){
        int x,y;scanf("%d%d",&x,&y);
        for(int i=x-1;i<y;i++){
            for(int j=0;j<M;j++){
                GROUND[i][j]=1;
            }
        }
    }
    for(int s=1;s<=G;s++){
        int x,y;scanf("%d%d",&x,&y);
        for(int j=x-1;j<y;j++){
            for (int i = 0; i < N; i++){
                GROUND[i][j]=1;
            }
        }
    }
    for(int i=0;i<N;i++){
        for(int j=0;j<M;j++){
            t+=GROUND[i][j];
        }
    }
    printf("%d\n",t);
}