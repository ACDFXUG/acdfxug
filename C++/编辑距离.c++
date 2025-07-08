#include <iostream>
#include <string>
#include <vector>

// 用最少的字符操作次数，将字符串 A 转换为字符串 B
int min_distance(std::string_view A,std::string_view B){
    const int al=A.size(),bl=B.size();
    std::vector<std::vector<int>> dp(al+1,std::vector<int>(bl+1,0));
    for(int i=1;i<=al;++i) dp[i][0]=i;
    for(int j=1;j<=bl;++j) dp[0][j]=j;
    for(int i=1;i<=al;++i){
        for(int j=1;j<=bl;++j){
            if(A[i-1]==B[j-1]) dp[i][j]=dp[i-1][j-1];
            else dp[i][j]=std::min(std::min(dp[i-1][j-1],dp[i-1][j]),dp[i][j-1])+1;
        }
    }
    return dp[al][bl];
}

int main(){
    std::string A,B;
    std::cin>>A>>B;
    std::cout<<min_distance(A,B)<<std::endl;
}