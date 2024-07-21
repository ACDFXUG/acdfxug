#include <iostream>

int INDEX(int *A,int n,int q){
    for(int l=0,r=n-1;l<=r;){
        int middle=l+(r-l)/2;
        if(A[middle]<q){
            l=middle+1;
        }else if(A[middle]>q){
            r=middle-1;
        }else{
            if((middle == 0)||(A[middle - 1]!=q)){
                return middle+1;
            }else{
                r=middle-1;
            }
        }
    }
    return -1;
}

int main(){
    int n,m,q;
    scanf("%d%d",&n,&m);
    int *A=new int[n];
    for(int i=0;i<n;i++){
        scanf("%d",&*(A+i));
    }
    for(int i=1;i<=m;i++){
        scanf("%d",&q);
        printf("%d ",INDEX(A,n,q));
    }
    delete[] A;
}