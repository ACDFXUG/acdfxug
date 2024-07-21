#include <iostream>

int main(){
    long long n;
    scanf("%lld",&n);
    for(;;){
        if(n%2==1&&n!=1){
                printf("%lld*3+1=%lld\n",n,3*n+1);
                n=n*3+1;
            }else if((n&1)==0){
                printf("%lld/2=%lld\n",n,n/2);
                n/=2;
            }else if(n==1){
                printf("End\n");
                break;
            }
    }
}