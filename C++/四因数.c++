#include <iostream>
#include <vector>
#include <tuple>
using namespace std;

tuple<int,int> factor_count(int n){
    int count=0;
    int factor_sum=0;
    for(int i=1;i*i<=n;i++){
        if(n%i==0){
            if(i*i==n){
                count++;
                factor_sum+=i;
            }else{
                count+=2;
                factor_sum+=i+n/i;
            }
        }
    }
    return {count,factor_sum};
}

int sumFourDivisors(vector<int>& nums) {
    int ans=0;
    for(int i=0;i<nums.size();i++){
        auto &&[count,factor_sum]=factor_count(nums[i]);
        if(count==4){
            ans+=factor_sum;
        }
    }
    return ans;
}

int main(){
}