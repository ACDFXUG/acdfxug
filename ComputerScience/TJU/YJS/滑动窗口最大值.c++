#include <iostream>
#include <map>
#include <vector>

using namespace std;

vector<int> maxSlidingWindow(vector<int>& nums, int k) {
    map<int,int> sorted;
    vector<int> res;
    for(int right(0),left(0);right<nums.size();right++){
        ++sorted[nums[right]];
        while(right-left+1>k){
            --sorted[nums[left]];
            if(sorted[nums[left]]==0) sorted.erase(nums[left]);
            ++left;
        }
        if(right-left+1==k) res.push_back(sorted.rbegin()->first);
    }
    return res;
}

int main(){
    vector<int> nums = {1,3,-1,-3,5,3,6,7};
    vector<int> res = maxSlidingWindow(nums,3);
    for(auto i:res) cout<<i<<" ";
    cout<<endl;
    return 0;
}