#include <iostream>
#include <stack>
#include <vector>

auto getNearestGreater(const std::vector<int> &h){
    int n=h.size();
    std::vector<int> leftMax(n,0);  // 存储左边部分的最大值
    std::vector<int> rightMax(n,0); // 存储右边部分的最大值
    // 从左到右遍历，计算左边部分的最大值
    int curMax=h[0];
    for (int i=1;i<n;++i) {
        leftMax[i]=curMax;
        curMax=std::max(curMax,h[i]);
    }
    // 从右到左遍历，计算右边部分的最大值
    curMax=h[n-1];
    for (int i=n-2;i>=0;--i){
        rightMax[i]=curMax;
        curMax=std::max(curMax,h[i]);
    }
    return std::pair{leftMax,rightMax};
}

int main(){
    int n;
    scanf("%d",&n);
    std::vector<int> height(n);
    for(int i=0;i<n;i++){
        scanf("%d",&height[i]);
    }
    const auto &[lm,rm]=getNearestGreater(height);
    int ans=0;
    for(int i=0;i<n;i++){
        if(lm[i]!=-1&&rm[i]!=-1){
            int h=std::min(lm[i],rm[i])-height[i];
            if(h>0) ans+=h;
        }
    }
    printf("%d\n",ans);
}