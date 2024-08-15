#include <iostream>

int main(){
    int n,sum=0;
    std::cin>>n;
    int* a=new int[n],*prefix=new int[n];
    for(int i=0;i<n;i++){
        scanf("%d",&a[i]);
        prefix[i]=sum+=a[i];
    }
    int m;
    std::cin>>m;
    for(int i=0;i<m;i++){
        int li,ri;
        scanf("%d%d",&li,&ri);
        printf("%d\n",li==1?prefix[ri-1]:prefix[ri-1]-prefix[li-2]);
    }
}