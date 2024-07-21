#include <iostream>
#define Ull unsigned long long

int main(){
    Ull ans=0ull;
    for(int i=0,x;i<64;i++){
        scanf("%d",&x);
        ans+=x*(1ull<<i);
    }
    printf("%llu\n",ans);
}