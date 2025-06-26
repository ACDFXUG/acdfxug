#include <iostream>
#include <unordered_map>

const std::unordered_map<std::string,char> morse{
    {".-",'A'},{"-...",'B'},{"-.-.",'C'},{"-..",'D'},
    {".",'E'},{"..-.",'F'},{"--.",'G'},{"....",'H'},
    {"..",'I'},{".---",'J'},{"-.-",'K'},{".-..",'L'},
    {"--",'M'},{"-.",'N'},{"---",'O'},{".--.",'P'},
    {"--.-",'Q'},{".-.",'R'},{"...",'S'},{"-",'T'},
    {"..-",'U'},{"...-",'V'},{".--",'W'},{"-..-",'X'},
    {"-.--",'Y'},{"--..",'Z'}
};

int main(){
    int n;
    std::cin>>n;
    std::string str;
    str.reserve(n);
    for(std::string s;n-->0;){
        std::cin>>s;
        str+=morse.at(s);
    }
    std::cout<<str<<std::endl;
}