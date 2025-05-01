#include <iostream>

int main(){
    int n;
    std::cin>>n;
    int *dp=new int[n+1](1);
    for(int i=1;i<=n;i++){
        for(int j=0;j<i;j++){
            dp[i]+=dp[j]*dp[i-j-1];
        }
    }
    std::cout<<dp[n];
    delete[] dp;
}