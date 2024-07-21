#include <iostream>
#include <cmath> 
#include <vector>
typedef long long ll;
typedef std::vector<ll> vll;

ll dot(vll a, vll b){
    ll ans = 0;
    for(int i = 0; i < a.size(); i++){
        ans += a[i] * b[i];
    }
    return ans;
}

int main(){
    int n,d,k;
    std::cin >> n >> d >> k;
    vll *v=new vll[n];
    for(int i = 0; i < n; i++){
        v[i].resize(d);
        for(int j=0;j<d;j++){
            scanf("%lld",&v[i][j]);
        }
    }
    for(int i=0;i<n;i++){
        for(int j=i+1;j<n;j++){
            if(dot(v[i],v[j])%k==0){
                printf("%d %d\n",i+1,j+1);
                goto END;
            }
        }
    }
    END:delete[] v;
}
