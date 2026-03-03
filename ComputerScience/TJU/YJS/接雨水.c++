#include <iostream>
#include <vector>

using namespace std;

int trap(vector<int>& height) {
    const int l=height.size();
    vector<int> left_max(l,0),right_max(l,0);
    int cur_max=height[0];
    for(int i=1;i<l;++i){
        left_max[i]=cur_max;
        cur_max=max(cur_max,height[i]);
    }
    cur_max=height[l-1];
    for(int i=l-2;i>=0;--i){
        right_max[i]=cur_max;
        cur_max=max(cur_max,height[i]);
    }
    int area=0;
    for(int i=0;i<l;++i){
        int S=min(left_max[i],right_max[i])-height[i];
        area+=S>=0?S:0;
    }
    return area;
}

int main(){
    vector<int> height={0,1,0,2,1,0,1,3,2,1,2,1};
    cout<<trap(height);
    return 0;
}