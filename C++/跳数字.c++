#include <iostream>
#include <queue>
#include <unordered_set>
#include <cmath>
#include <print>
using ll=long long;

int main(){
    int n,k,cnt=0;
    std::cin>>n>>k;
    std::queue<ll> nums;
    std::unordered_set<ll> vis;
    for(int i=0;i<k;nums.push(i++));
    while(!nums.empty()){
        ll cur=nums.front();
        nums.pop();
        for(int i=0;i<k;++i){    
            if(ll nxt=cur*10+i;nxt>=1&&nxt<=n&&!vis.contains(nxt))[[unlikely]]{
                nums.push(nxt);
                vis.insert(nxt);
            }
        }
    }
    std::println("{}",vis.size());
}
