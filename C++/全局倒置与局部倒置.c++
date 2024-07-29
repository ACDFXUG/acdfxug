#include <iostream>
#include <vector>

int fullReverse(std::vector<int>& nums){
    int l=nums.size(),ans=0;
    for(int i=0;i<l;i++){
        for(int j=i+1;j<l;j++){
            if(nums[i]>nums[j]){
                ans++;
            }
        }
    }
    return ans;
}

int partReverse(std::vector<int>& nums){
    int l=nums.size(),ans=0;
    for(int i=0;i<l-1;i++){
        if(nums[i]>nums[i+1]){
            ans++;
        }
    }
    return ans;
}

bool isIdealPermutation(std::vector<int>& nums) {
    return fullReverse(nums)==partReverse(nums);
}

int main(){
    std::vector<int> nums={1,2,3,4,5,6,7,8,9,10};
    printf(fullReverse(nums)==partReverse(nums)?"Yes":"No");
}