#include <iostream>
#include <vector>
#include <unordered_set>
#include <unordered_map>

using namespace std;

template<class T>
auto pairHash=[](const pair<T,T>& p){
    size_t h1=hash<T>{}(p.first);
    size_t h2=hash<T>{}(p.second);
    return h1^(h2+0x9e3779b9+(h1<<6)+(h1>>2));
};

int subarraySum(vector<int>& nums, int k) {
    unordered_map<int,int> prefix;
    prefix[0]=1;
    int sum=0,res=0;
    for(auto num:nums){
        sum+=num;
        if(prefix.count(sum-k)){
            res+=prefix[sum-k];
        }
        ++prefix[sum];
    }
    return res;
}

int main(){
    vector<int> nums={1,0,1};
    cout<<subarraySum(nums,1)<<endl;
    return 0;
}