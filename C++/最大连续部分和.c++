#include <iostream>
#include <vector>
#include <algorithm>

int main(){
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);
    
    int n;
    std::cin>>n;
    std::vector<int> a(n);
    for(int i=0;i<n;std::cin>>a[i++]);
    std::vector<int64_t> dp(n);
    dp[0]=a[0];
    long long max=dp[0];
    for(int i=1;i<n;i++){
        dp[i]=std::max(dp[i-1]+a[i],static_cast<int64_t>(a[i]));
        max=std::max(max,dp[i]);
    }
    std::cout<<max<<std::endl;
}