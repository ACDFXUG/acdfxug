#include <iostream>

int max(int *a,int n){
    int max=0x80000000;
    for(int i=0;i<n;i++){
        max=a[i]>max?a[i]:max;
    }
    return max;
}

int main(){
    int a[101](0),n;
    std::cin>>n;
    for(int i=0,x;i<n;i++){
        scanf("%d",&x);
        a[x]++;
    }
    std::cout<<max(a,101)<<"\n";
}