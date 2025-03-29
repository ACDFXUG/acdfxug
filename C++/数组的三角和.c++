#include <iostream>
#include <vector>
using namespace std;

int triangularSum(vector<int>& nums) {
    while (nums.size() > 1) {
        vector<int> next;
        for (size_t i = 0; i < nums.size() - 1; ++i) {
            next.push_back((nums[i] + nums[i + 1]) % 10);
        }
        nums = move(next); // 更新为下一层的数组
    }
    return nums.empty() ? 0 : nums[0];
}

int main(){
    vector nums{1,2,3,4,5};
    cout<<triangularSum(nums);
}