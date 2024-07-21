#include <iostream>
int A[300][300];
void r_chg(int A[300][300],int N,int i,int j){
    for(int s=0;s<N;i++){
        std::swap(A[i][s],A[j][s]);
    }
}
void c_chg(int A[300][300],int N,int a,int b){
    for(int i=0;i<N;i++){
        std::swap(A[i][a],A[i][b]);
    }
}
int sum(int A[300][300],int a,int b,int c,int d){
    int ans=0;
    for(int i=a;i<=c;i++){
        for(int j=b;j<=d;j++){
            ans+=A[i][j];
        }
    }
    return ans;
}
int main(){
    int N,q;
    scanf("%d%d",&N,&q);
    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            A[i][j]=N*i+j;
        }
    }
    for(int s=1;s<=q;s++){
        int op;
        scanf("%d",&op);
        if(op==1){
            int i,j;
            scanf("%d%d",&i,&j);
            r_chg(A,N,i,j);
        }else if (op==2){
            int a,b;
            scanf("%d%d",&a,&b);
            c_chg(A,N,a,b);
        }else if(op==3){
            int a,b,c,d;
            scanf("%d%d%d%d",&a,&b,&c,&d);
            printf("%d\n",sum(A,a,b,c,d));
        }
    }
}
