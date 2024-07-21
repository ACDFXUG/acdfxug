#include <iostream>

int main(){
    int n,m;
    scanf("%d %d",&n,&m);
    int *A=new int[n+1];
    for(int i=1;i<=n;i++){
        scanf("%d",&A[i]);
    }
    for(int i=1;i<=m;i++){
        int act;
        scanf("%d",&act);
        if(act==1){
            int x,y;
            scanf("%d %d",&x,&y);
            for(int k=x;k<=n;k+=x){
                A[k]+=y;
            }
        }else if(act==2){
            int j;
            scanf("%d",&j);
            printf("%d\n",A[j]);
        }
    }
    delete[] A;
}