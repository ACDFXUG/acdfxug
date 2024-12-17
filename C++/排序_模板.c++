#include <iostream>
#include <algorithm>

int main(){
    int N;
    scanf("%d",&N);
    int *a=new int[N];
    for(int i=0;i<N;scanf("%d",a+i++));
    std::sort(a,a+N);
    for(int i=0;i<N;i++){
        printf(i==N-1?"%d\n":"%d ",a[i]);
    }
    delete[] a;
}