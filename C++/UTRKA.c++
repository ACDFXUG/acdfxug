#include <iostream>
#include <unordered_map>
#include <algorithm>
#include <ranges>

int main(){
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    std::cout.tie(NULL);

    int n;
    std::cin>>n;
    std::unordered_map<std::string,int> count;
    for(int i=0;i<n;i++){
        std::string s;
        std::cin>>s;
        ++count[s];
    }
    for(int i=1;i<n;i++){
        std::string s;
        std::cin>>s;
        --count[s];
    }
    using namespace std::ranges;
    auto name=count 
        | views::filter([](std::pair<std::string,int> p){return p.second>0;})
        | views::keys;
    std::cout<<name.front()<<std::endl;
}