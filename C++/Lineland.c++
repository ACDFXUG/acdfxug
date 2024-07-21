#include <iostream>
#include <algorithm>

int maxDis(int *a,int n,int i){
    int p=a[i],max=-0x7fffffff-1;
    for(int s=0;s<n;s++){
        if(s==i)continue;
        max=(abs(a[s]-p)>max)?abs(a[s]-p):max;
    }
    return max;
}

int minDis(int *a,int n,int i){
    int p=a[i],min=0x7fffffff;
    for(int s=0;s<n;s++){
        if(s==i)continue;
        min=(abs(a[s]-p)<min)?abs(a[s]-p):min;
    }
    return min;
}

int main(){
    int n;
    std::cin>>n;
    int *a=new int[n];
    for(int i=0;i<n;scanf("%d",a+i++));
    for(int i=0;i<n;i++){
        printf("%d %d\n",minDis(a,n,i),maxDis(a,n,i));
    }
    delete[] a;
}

