#include <iostream>
#define String std::string

bool isLucky(String s){
    for(char x:s){
        if(!(x=='7'||x=='4')){
            return false;
        }
    }
    return true;
}

bool isNearlyLucky(String s){
    int t=0;
    for(char p:s){
        if(p=='7'||p=='4'){
            t++;
        }
    }
    return isLucky(std::to_string(t));
}

int main(){
    String n;
    std::cin>>n;
    printf(isNearlyLucky(n)?"YES\n":"NO\n");
}