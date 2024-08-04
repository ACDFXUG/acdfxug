#include <iostream>
#include <vector>
using namespace std;

vector<int> decompressRLElist(vector<int>& nums) {
    vector<int> ans;
    for(int i=0;i<nums.size();i+=2){
        for(int j=0;j<nums[i];j++){
            ans.push_back(nums[i+1]);
        }
    }
    return ans;
}

int main(){
    vector<int> nums = {1,2,3,4};
    vector<int> ans = decompressRLElist(nums);
    for(int x:ans){
        cout<<x<<" ";
    }
}