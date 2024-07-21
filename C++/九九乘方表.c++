#include <iostream>

int POWER(int a,int n){
    int one=1;
    for(int i=1;i<=n;i++){
        one*=a;
    }
    return one;
}

int main(){
    int n;
    std::cin>>n;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=i;j++){
            printf(j==i?"%d ^ %d = %d\n":"%d ^ %d = %d ",i,j,POWER(i,j));
        }
    }
}