#include <iostream>

size_t count_paths(int n,int m){
    int **dp=new int *[n];
    for(int i=0;i<n;dp[i++]=new int[m]);
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            if(i==0||j==0)dp[i][j]=1;
            else dp[i][j]=dp[i-1][j]+dp[i][j-1];
        }
    }
    return dp[n-1][m-1];
}

size_t to17num(size_t num){//保留17位有效数字
    return num%10000000000000000;
}

int main(){
    int n,m;
    std::cin>>n>>m;
    std::cout<<to17num(count_paths(n,m));
    return 0;
}