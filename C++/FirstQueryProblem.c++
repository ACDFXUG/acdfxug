#include <iostream>

int main(){
    int N,Q;scanf("%d",&N);
    int *A=new int[N];
    for(int i=0;i<N;scanf("%d",A+i++));
    for(scanf("%d",&Q);Q--;){
        int op;scanf("%d",&op);
        if(op==1){
            int k,x;
            std::cin>>k>>x;
            A[k-1]=x;
        }else if(op==2){
            int k;std::cin>>k;
            printf("%d\n",A[k-1]);
        }
    }
    delete[] A;
}