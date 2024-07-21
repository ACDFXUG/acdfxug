#include <iostream>
#define String std::string

bool contains(String s,char x){
    for(char p:s){
        if(p==x){
            return true;
        }
    }
    return false;
}

int main(){
    String s;
    std::cin>>s;
    unsigned long long sum=0;
    for(char x:s){sum+=x-'0';}
    printf((contains(s,'3')||sum%3==0)?"YES":"NO");
}


