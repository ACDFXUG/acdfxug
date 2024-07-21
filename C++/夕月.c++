#include <iostream>
#define ll long long

int main(){
    ll n;
    scanf("%lld",&n);
    for(ll i=1e18;i>=0;i--){
        if(i+1e18==1e18){
            printf("%d\n",i);
            break;
        }
    }
}