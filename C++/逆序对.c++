#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>
#define FAST_IO do{\
    std::ios::sync_with_stdio(false);\
    std::cin.tie(nullptr);\
    std::cout.tie(nullptr);\
}while(0)
using ll=long long;

ll low_bit(ll x){
    return x&(-x);
}

ll query(std::vector<ll> &tree,ll x){
    ll ans=0;
    for(;x;x-=low_bit(x)){
        ans+=tree[x];
    }
    return ans;
}

void update(std::vector<ll> &tree,ll x,const ll &val){
    for(;x<tree.size();x+=low_bit(x)){
        tree[x]+=val;
    }
}

ll count_inversion(std::vector<ll> &arr){// 快速统计逆序对
    auto sorted(arr);
    std::sort(sorted.begin(),sorted.end());
    std::unordered_map<ll,ll> rank;
    ll rk=1;
    for(const ll &num:sorted){
        if(!rank.contains(num)){
            rank[num]=rk++;
        }
    }
    const ll &n=arr.size();
    std::vector<ll> tree(n+1,0);
    ll ans=0;
    for(ll i=n-1;i>=0;--i){
        const ll &r=rank[arr[i]];
        ans+=query(tree,r-1);
        update(tree,r,1);
    }
    return ans;
}

int main(){
    FAST_IO;
    ll n;
    std::cin>>n;
    std::vector<ll> arr(n);
    for(ll i=0;i<n;++i){
        std::cin>>arr[i];
    }
    std::cout<<count_inversion(arr)<<'\n';
}