#include <iostream>
#include <vector>
#include <print>
using namespace std;
void nextPermutation(vector<int> &nums) {
    next_permutation(nums.begin(), nums.end());
}

int main(){
    vector<int> nums={2,3,1};
    nextPermutation(nums);
    for(const int &v:nums){
        std::println("{}",v);
    }
}