#include <iostream>
#define String std::string

int main(){
    String A,B;
    std::cin>>A>>B;
    printf("%s\n",A.length()>B.length()?A.c_str():B.c_str());
}