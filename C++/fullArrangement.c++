#include <iostream>
#include <vector>
#define Vector std::vector

void backtrack(Vector<int> &nums,Vector<Vector<int>> &res,int start){
    if(start==nums.size()){
        res.push_back(nums);
        return;
    }
    for(int i=start;i<nums.size();i++){
        std::swap(nums[i],nums[start]);
        backtrack(nums,res,start+1);
        std::swap(nums[i],nums[start]);
    }
}

Vector<Vector<int>> fullArrangement(int N){
    Vector<int> nums;
    for(int i=1;i<=N;i++){
        nums.push_back(i);
    }
    Vector<Vector<int>> res;
    backtrack(nums,res,0);
    return res;
}

int main(){
    Vector<Vector<int>> res=fullArrangement(4);
    for(auto i:res){
        for(auto j:i){
            printf("%d ",j);
        }
        printf("\n");
    }
}