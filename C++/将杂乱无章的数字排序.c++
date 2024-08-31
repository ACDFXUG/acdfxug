#include <iostream>
#include <unordered_map>
#include <vector>
#include <algorithm>
using namespace std;
int mapping_number(vector<int> &mapping,int &x){
    string X=to_string(x),ans="";
    for(int i=0,l=X.length();i<l;i++){
        ans+=char(mapping[X[i]-'0']+'0');
    }
    return stoi(ans);
}

vector<int> sortJumbled(vector<int>& mapping, vector<int>& nums) {
    int n=nums.size();
    unordered_map<int,int> mapped;
    for(int i=0,x;i<n;i++){
        x=mapping_number(mapping,nums[i]);
        mapped[nums[i]]=x;
    }
    stable_sort(nums.begin(),nums.end(),[&mapped](const int &x,const int &y){
        int mapx=mapped[x],mapy=mapped[y];
        return mapx<mapy;
    });
    return nums;
}

int main(){
    vector<int> mapping={8,9,4,0,2,1,3,5,7,6},nums={991,338,38};
    vector<int> ans=sortJumbled(mapping,nums);
    for(auto x:ans) cout<<x<<" ";
    return 0;
}