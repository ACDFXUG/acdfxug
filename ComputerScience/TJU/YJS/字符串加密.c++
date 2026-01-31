#include <iostream>

char encrypt(char c){
    if(c>='a'&&c<='z') return char((c+1-'a')%26+'a');
    else if(c>='A'&&c<='Z') return char(c+32);
    else return c;
}

int main(){
    int T;
    std::cin>>T;
    std::cin.ignore();
    for(std::string str;T-->0;){
        std::getline(std::cin,str);
        for(const char &c:str) std::cout<<encrypt(c);
        std::cout<<std::endl;
    }
}