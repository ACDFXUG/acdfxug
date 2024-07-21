#include <iostream>
typedef unsigned long long Ull;

Ull gcd(Ull n,Ull m){
    Ull k=0;
    if((n&1)||(m&1)){
        for(;n!=m;(n>m)?n-=m:m-=n);
    }else{
        for(;!(n&1)&&!(m&1);n>>=1,m>>=1,k++);
        for(;n!=m;(n>m)?n-=m:m-=n);
    }
    return m<<k; 
}

int main(){
    Ull a,b;
    scanf("%llu%llu",&a,&b);
    printf("%llu\n",gcd(a,b));
}