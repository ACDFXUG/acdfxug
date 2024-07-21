#include <iostream>
int FACT(int n){  //阶乘
    int fact=1;
    for(int i=1;i<=n;i++){
        fact*=i;
    }
    return fact;
}

int A(int m,int n){  //排列，m在下面
    return FACT(m)/FACT(m-n);
}

int C(int m,int n){  //组合，m在下面
    return A(m,n)/A(n,n);
}

int main(){
    int n;
    scanf("%d",&n);
    printf("%d",FACT(n));
}