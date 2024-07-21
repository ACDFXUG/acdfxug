#include <iostream>
#define String std::string

int main(){
    String sen[3],num,ans="";
    for(int i=0;i<3;std::cin>>sen[i++]);
    std::cin>>num;
    int l=num.length();
    for(int i=0;i<l;i++){
        int index=num[i]-'0';
        ans+=sen[index-1];
    }
    std::cout<<ans<<std::endl;
}