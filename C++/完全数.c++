#include <iostream>
#include <vector>
typedef long long ll;

ll factor_sum(ll n){
    ll sum=0ll;
    for(ll i = 1; i*i<=n; i++){
        if(n % i == 0){
            sum+=i;
            if(i*i!=n){
                sum+=n/i;
            }
        }
    }
    return sum-n;
}

int main(){
    ll n,sum;
    scanf("%lld",&n);
    sum=factor_sum(n);
    if(sum==n){
        printf("Perfect\n");
    }else if(sum>n){
        printf("Abundant\n");
    }else{
        printf("Deficient\n");
    }
}