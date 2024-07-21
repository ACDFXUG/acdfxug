#include <iostream>

int gcd(int a,int b) {
    return b>0?gcd(b,a%b):a;
}

int main(){
    int t;
    scanf("%d",&t);
    for(int i=0;i<t;i++){
        int n;
        scanf("%d",&n);
        if(n==0){
            printf("%d\n",0);
        }else{
            if((2*n+2)/gcd(n,2*n+2)!=1)
                printf("%d/%d\n",n/gcd(n,2*n+2),(2*n+2)/gcd(n,2*n+2));
            else
                printf("%d\n",n/gcd(n,2*n+2));
        }
    }
}