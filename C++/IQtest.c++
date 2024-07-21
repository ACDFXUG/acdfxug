#include <iostream>

int indexOfOdd(int *a,int n){
    for(int i=0;i<n;i++){
        if(a[i]==1){
            return i;
        }
    }
    return -1;
}

int indexOfEven(int *a,int n){
    for(int i=0;i<n;i++){
        if(a[i]==0){
            return i;
        }
    }
    return -1;
}

int main(){
    int n,even,odd;
    std::cin>>n;
    int *IQ=new int[n];
    for(int x,i=0;i<n;i++){
        scanf("%d",&x);
        IQ[i]=x&1;
        IQ[i]==1?odd++:even++;
    }
    printf("%d\n",odd>even?indexOfEven(IQ,n)+1:indexOfOdd(IQ,n)+1);
}   