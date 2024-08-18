#include <iostream>
typedef long long ll;

int main(){
    int n,k;
    ll sum=0ll;
    std::cin>>n>>k;
    ll *prefix=new ll[n+1](0ll);
    for(ll i=1,ai;i<=n;i++){
        scanf("%lld",&ai);
        prefix[i]=prefix[i-1]+ai;
    }
    for(int i=k;i<=n;i++){
        sum+=prefix[i]-prefix[i-k];
    }
    std::cout<<sum<<std::endl;
    delete[] prefix;
}