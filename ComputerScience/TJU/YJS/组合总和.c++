#include <print>
#include <vector>

using namespace std;

void backtrack(const vector<int> &candidates,int target,vector<int> &path,vector<vector<int>> &res){
    if(target < 0) return;
    if(target == 0) res.push_back({path});
    for(int i = 0; i < candidates.size(); i++){
        path.push_back(candidates[i]);
        backtrack(candidates,target-candidates[i],path,res);
        path.pop_back();
    }
}

vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
    vector<vector<int>> res;
    vector<int> path;
    backtrack(candidates,target,path,res);
    for(auto &v:res) sort(v.begin(),v.end());
    sort(res.begin(),res.end());
    res.erase(unique(res.begin(),res.end()),res.end());
    return res;
}

int main(){
    vector<int> can={2,3,6,7};
    println("{}",combinationSum(can,7));
}