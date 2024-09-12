#include <iostream>
#include <map>
#include <print>

int main(){
    int n;
    std::cin>>n;
    std::string p="";
    std::map<char,int> frst;
    for(int i=0;i<n;i++){
        char s[31];
        scanf("%s",s);
        frst[s[0]]++;
    }
    for(auto &[chr,cnt]:frst){
        if(cnt>=5){
            p+=chr;
        }
    }
    printf(p.empty()?"PREDAJA\n":"%s\n",p.c_str());
}