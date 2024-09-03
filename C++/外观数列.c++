#include <iostream>
#include <regex>

const std::regex dup("(\\d)\\1*");

std::string RLE(std::string str){
    std::smatch match;
    std::string ans("");
    while(std::regex_search(str, match, dup)){
        int l=match[0].length();
        ans+=std::to_string(l)+match[1].str();
    }
    return ans;
}

std::string countAndSay(int n){
    if(n==1){
        return "1";
    }else{
        return RLE(countAndSay(n-1));
    }
}

int main(){
    printf("%s\n", countAndSay(5).c_str());
    return 0;
}