#include <iostream>
#include <set>
#include <unordered_set>
#include <unordered_map>
#include <array>
#include <stack>
#include <print>
#include <algorithm>

std::unordered_set<std::string> valid{};

const std::unordered_map<int,std::array<char,3>> NAME={
    {2,{'A','B','C'}},
    {3,{'D','E','F'}},
    {4,{'G','H','I'}},
    {5,{'J','K','L'}},
    {6,{'M','N','O'}},
    {7,{'P','R','S'}},
    {8,{'T','U','V'}},
    {9,{'W','X','Y'}},
};

// void backtrack(std::string_view digits,int index,std::set<std::string> &names,std::string name){
//     if(index==digits.size()&&valid.contains(name)){
//         names.insert(name);
//         return;
//     }else if(index<digits.size())
//         for(auto &c:NAME.at(digits[index]-'0')){
//             name.push_back(c);
//             backtrack(digits,index+1,names,name);
//             name.pop_back();
//         }
// }

std::set<std::string> dfs(std::string_view digits){
    std::set<std::string> ans;
    std::stack<std::string> dfs;
    std::unordered_set<std::string> visited;
    dfs.emplace("");
    visited.emplace("");
    while(!dfs.empty()){
        auto cur_name=dfs.top();
        dfs.pop();
        if(cur_name.size()==digits.size()&&valid.contains(cur_name))
            ans.emplace(cur_name);
        else if(cur_name.size()<digits.size()){
            for(auto &c:NAME.at(digits[cur_name.size()]-'0')){
                auto nxt=cur_name+c;
                if(!visited.contains(nxt)){
                    dfs.emplace(nxt);
                    visited.emplace(nxt);
                }
            }
        }
    }
    return ans;
}

int main(){
    std::string digits,tmp;
    std::cin>>digits;
    for(int i=0;i<4617;++i){
        std::cin>>tmp;
        valid.emplace(tmp);
    }
    auto set=dfs(digits);
    if(set.empty())[[unlikely]] std::println("NONE");
    else std::ranges::for_each(set,[](auto &&x){std::println("{}",x);});
}