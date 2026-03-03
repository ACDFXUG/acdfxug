#include <iostream>
#include <algorithm>
#include <unordered_map>
#include <vector>

using namespace std;
vector<vector<string>> groupAnagrams(vector<string>& strs) {
    unordered_map<string,vector<string>> map;
    for(const auto &str:strs){
        auto tmp=str;
        sort(tmp.begin(),tmp.end());
        map[tmp].push_back(str);
    }
    vector<vector<string>> ans;
    for(auto it=map.begin();it!=map.end();++it){
        ans.push_back(it->second);
    }
    return ans;
}

int main(){
    vector<string> strs{"eat","tea","tan","ate","nat","bat"};
    auto ans=groupAnagrams(strs);
    for(auto vec:ans){
        for(auto str:vec){
            cout<<str<<" ";
        }
        cout<<endl;
    }
}