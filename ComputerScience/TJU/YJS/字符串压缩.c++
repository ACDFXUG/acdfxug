#include <iostream>
#define conRef(name) const auto &name

std::string compress(std::string_view str){
    std::string cmprs("");
    for(int i=0;i<str.size();){
        conRef(c)=str[i];
        int count=0;
        while(i<str.size()&&str[i]==c){
            ++i;++count;
        }
        cmprs+=std::to_string(count);
        cmprs+=c;
    }
    return cmprs;
}

int main(){
    int T;
    std::cin>>T;
    std::cin.ignore();
    for(std::string str;T-->0;){
        std::getline(std::cin,str);
        std::cout<<compress(str)<<std::endl;
    }
}