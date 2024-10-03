#include <iostream>
#include <vector>

auto quick_sort(std::vector<int> &nums,int l,int r){
    if(l>=r) return;
    int i=l,j=r,p=nums[l];
    while(i<j){
        while(i<j&&nums[j]>=p) j--;
        nums[i]=nums[j];
        while(i<j&&nums[i]<=p) i++;
        nums[j]=nums[i];
    }
    nums[i]=p;
    quick_sort(nums,l,i-1);
    quick_sort(nums,i+1,r);
}

void sortColors(std::vector<int>& nums) {
    quick_sort(nums,0,nums.size()-1);
}