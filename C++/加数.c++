#include <iostream>

int main(){
    int n;
    std::cin>>n;
    std::string a=std::to_string(n);
    for(;n>0;a+=std::to_string(n>>=1));
    std::cout<<a.length()-1<<std::endl;
}