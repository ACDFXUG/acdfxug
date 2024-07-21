#include <iostream>

int main(){
    int n,k;
    scanf("%d%d",&n,&k);
    int *a=new int[k+1],*h=new int[n+1];
    for(int i=1;i<=k;i++){
        scanf("%d",a+i);
    }
    for(int i=0;i<k;i++){
        scanf("%d",h+i);
    }
    for(int s=k;s<=n;s++){
        for(int i=1;i<=k;i++){
            h[s]+=(a[i]*h[s-i])%1000000007;
        }
    }
    printf("%d\n",h[n]%1000000007);
    delete[] a,h;
}