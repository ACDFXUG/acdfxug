#include <iostream>
#include <unordered_map>
#include <vector>
#include <algorithm>
typedef std::string String;

int main(){
    int n,max=0;
    std::cin>>n;
    std::unordered_map<String,int> vote_count;
    for(int i=0;i<n;i++){
        String str;
        std::cin>>str;
        vote_count[str]++;
    }
    for(auto &[_,cnt]:vote_count){
        max=std::max(max,cnt);
    }
    std::vector<std::pair<String,int>> ans;
    for(auto &[name,cnt]:vote_count){
        if(cnt==max){
            ans.push_back({name,cnt});
        }
    }
    std::sort(ans.begin(),ans.end(),[](auto &a,auto &b){
        return a.first<b.first;
    });
    for(auto &[name,_]:ans){
        printf("%s\n",name.data());
    }
}