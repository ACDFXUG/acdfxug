#include <iostream>
#include <vector>
#include <algorithm>

int main(){
    int n;
    std::cin>>n;
    std::vector<std::string> strs(n);
    for(std::string s;auto &str:strs){
        std::cin>>s;
        str=s;
    }
    std::sort(strs.begin(),strs.end(),[](auto &&a,auto &&b){return a+b>b+a;});
    for(auto &str:strs){
        std::cout<<str;
    }
    std::cout<<std::endl;
}