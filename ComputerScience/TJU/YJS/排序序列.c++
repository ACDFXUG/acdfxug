#include <print>
#include <vector>

using namespace std;

void backtrack(const vector<int> &nums,int k,string &res,vector<string> &ans,vector<bool> &used){
    if(k==0){
        ans.push_back(res);
        return;
    }
    for(int i=0;i<nums.size();i++){
        if(used[i]) continue;
        used[i]=true;
        res+=nums[i]+'0';
        backtrack(nums,k-1,res,ans,used);
        res.pop_back();
        used[i]=false;
    }
}

string getPermutation(int n, int k) {
    vector<int> nums;
    for(int i=1;i<=n;nums.push_back(i++));
    vector<string> ans;
    string res;
    auto used=vector<bool>(n,false);
    backtrack(nums,n,res,ans,used);
    return ans[k-1];
}

int main(){
    println("{}",getPermutation(4,9));
}