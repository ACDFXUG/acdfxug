#include <iostream>
typedef long long ll;

int main(){
    int n;
    scanf("%d",&n);
    ll t=0,*A=new ll[n];
    for(int i=0;i<n;scanf("%lld",A+i++));
    for(int i=1;i<n;i++){
        t+=llabs(A[i]-A[i-1]);
    }
    printf("%lld\n",t);
}
