#include <iostream>
#include <algorithm>
#include <format>
typedef std::string String;

int main(){
    unsigned long long N;
    std::cin>>N;
    String a=std::format("{:b}",N);
    int L=a.length()/7,r=a.length()%7;
    String b=String(7-r,'0')+a;
    String *hex=new String[L+1];
    for(int i=L;i>=0;i--){
        hex[i]=b.substr(7*i,7);
        hex[i]=(i?"1":"0")+hex[i];
        hex[i]=std::format("{:02X}",std::stoi(hex[i],nullptr,2));
    }
    for(int i=L;i>=0;i--){
        printf("%s ",hex[i].c_str());
    }
    printf("\n");
    delete[] hex;
}