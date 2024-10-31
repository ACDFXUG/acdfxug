#include <iostream>
#include <unordered_map>
#include <algorithm>

const std::unordered_map<char,char> mirror={
    {'b','d'},{'d','b'},{'p','q'},{'q','p'}
};

bool is_mirror(const std::string &str){
    auto s=str;
    std::reverse(s.begin(),s.end());
    for(auto &c:s){
        c=mirror.at(c);
    }
    return s==str;
}

int main(){
    std::string str;
    std::cin>>str;
    printf(is_mirror(str)?"Yes\n":"No\n");
}
