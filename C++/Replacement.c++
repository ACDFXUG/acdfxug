#include <iostream>
#define String std::string

String replaceAll(String S,char c,char d){
    int l=S.length();
    String s=S;
    for(int i=0;i<l;i++){
        if(s[i]==c){
            s[i]=d;
        }
    }
    return s;
}

int main(){
    int N,Q;
    char c,d;
    String S;
    std::cin>>N>>S>>Q;
    for(;Q--;){
        std::cin>>c>>d;
        S=replaceAll(S,c,d);
    }
    printf("%s\n",S.c_str());
}