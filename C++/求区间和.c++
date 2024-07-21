#include <iostream>

static int all_sum(int *a,int l,int r){
    int sum=0;
    for(int i=l-1;i<r;i++){
        sum+=*(a+i);
    }
    return sum;
}

int main(){
    int n,m;
    scanf("%d",&n);
    int *a=new int[n];
    for(int i=0;i<n;i++){
        scanf("%d",&*(a+i));
    }
    scanf("%d",&m);
    for(int i=0;i<m;i++){
        int li,ri;
        scanf("%d%d",&li,&ri);
        printf("%d\n",all_sum(a,li,ri));
    }
    delete[] a;
}