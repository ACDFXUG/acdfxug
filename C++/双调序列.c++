#include <iostream>
#include <algorithm>

int main(){
    int n;
    std::cin>>n;
    int *a=new int[n];
    for(int i=0;i<n;scanf("%d",a+i++));
    std::sort(a,a+n,(int &x,int &y){
        return x>y;
    });
    for(int l=0,r=n-1;l<=r;l++,r--){
        if(l!=r){
            printf("%d\n%d\n",a[l],a[r]);
        }else{
            printf("%d\n",a[l]);
        }
    }
    delete[] a;
}