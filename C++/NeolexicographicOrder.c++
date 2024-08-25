#include <iostream>
#include <unordered_map>
#include <vector>
#include <algorithm>

std::string change(
    std::string &str,
    std::unordered_map<char,char> &neo_lex){
    std::string ans="";
    for(int i=0,l=str.size();i<l;i++){
        ans+=neo_lex[str[i]];
    }
    return ans;
}

int main(){
    std::unordered_map<char,char> neo_lex;
    std::vector<std::pair<std::string,std::string>> order;
    std::string lex;
    std::cin>>lex;
    for(int i=0,l=lex.size();i<l;i++){
        neo_lex[lex[i]]=char('a'+i);
    }
    int n;
    std::cin>>n;
    while(n-->0){
        std::string str;
        std::cin>>str;
        order.push_back({str,change(str,neo_lex)});
    }
    std::sort(order.begin(),order.end(),[](auto s1,auto s2){
        return s1.second<s2.second;
    });
    for(auto &s:order){
        printf("%s\n",s.first.data());
    }
}