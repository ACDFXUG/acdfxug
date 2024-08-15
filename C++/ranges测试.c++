#include <iostream>
#include <ranges>
#include <vector>
#include <algorithm>
using namespace std;
using namespace std::views;

int main(){
    std::vector<std::string> v={"aaa","sdfbsa","sadfa","dfasgr","dsfasfdsaf","dsfsfffsa"};
    auto stream=v|filter([](std::string x){
        return x.length()<=6;
    });
    for(auto &s:stream){
        cout<<s<<endl;
    }
}