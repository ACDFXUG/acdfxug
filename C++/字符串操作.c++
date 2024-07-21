#include <iostream>
#define input std::cin>>
#define output std::cout<<
#define endl <<std::endl;
#define string std::string

string repeat(char c,int count){
    string s="";
    for (int i = 0; i < count; i++){
        s+=c;
    }
    return s;
}

int main(){
    char c;
    input c;
    output repeat(c,5) endl;
}