#include <iostream>
#include <vector>
#include <print>

using namespace std;

void moveZeroes(vector<int> &nums) {
    int zeroCnt=0;
    for(const int &num:nums) zeroCnt+=(num==0);
    while(zeroCnt-->0){
        int start=0;
        for(int i=0;i<nums.size();++i){
            if(nums[i]==0){
                start=i;
                break;
            }
        }
        for(int i=start;i<nums.size()-1;++i){
            nums[i]=nums[i+1];
        }
        nums[nums.size()-1]=0;
    }
}

int main() {
    vector<int> nums={0,1,0,3,12};
    moveZeroes(nums);
    println("{}",nums);
    return 0;
}