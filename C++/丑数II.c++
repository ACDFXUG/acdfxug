#include <iostream>
#include <cmath>

int nthUglyNumber(int n) {
    int *dp=new int[n](1);
    int p2=0,p3=0,p5=0; // 初始化三个指针

    for (int i=1;i<n;++i) {
        int P2=dp[p2]<<1,P3=dp[p3]*3,P5=dp[p5]*5;
        dp[i]=std::min({P2,P3,P5}); // 计算当前最小丑数
        if (dp[i]==P2) ++p2; // 更新指针
        else if (dp[i]==P3) ++p3;
        else if (dp[i]==P5) ++p5;
    }

    const int ans=dp[n-1]; // 获取第n个丑数
    delete[] dp; // 释放动态数组
    return ans;
}

int main(){
    std::cout<<nthUglyNumber(10)<<std::endl;
}