#include <print>
#include <vector>

using namespace std;

void backtrack(const vector<int> &candi,int target,int start,vector<int> &path,vector<vector<int>> &res){
    if(target<0) return;
    if(target==0) res.push_back({path});
    for(int i=start;i<candi.size();i++){
        if(i>start && candi[i]==candi[i-1]) continue;
        path.push_back(candi[i]);
        backtrack(candi,target-candi[i],i+1,path,res);
        path.pop_back();
    }
}

vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
    vector<vector<int>> res;
    vector<int> path;
    sort(candidates.begin(),candidates.end());
    backtrack(candidates,target,0,path,res);
    sort(res.begin(),res.end());
    res.erase(unique(res.begin(),res.end()),res.end());
    return res;
}

int main(){
    vector candi{10,1,2,7,6,1,5};
    int target=8;
    println("{}",combinationSum2(candi,target));
}