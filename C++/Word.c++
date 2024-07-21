#include <iostream>
#define String std::string

int upper(String s){
    int t=0;
    for(int i=0;i<s.length();i++){
        if(s[i]>='A'&&s[i]<='Z'){
            t++;
        }
    }
    return t;
}

int lower(String s){
    int t=0;
    for(int i=0;i<s.length();i++){
        if(s[i]>='a'&&s[i]<='z'){
            t++;
        }
    }
    return t;
}

String toUpper(String s){
    int l=s.length();
    for(int i=0;i<l;i++){
        s[i]=std::toupper(s[i]);
    }
    return s;
}

String toLower(String s){
    int l=s.length();
    for(int i=0;i<l;i++){
        s[i]=std::tolower(s[i]);
    }
    return s;
}

int main(){
    String s;
    std::cin>>s;
    s=(lower(s)<upper(s))?toUpper(s):toLower(s);
    printf("%s\n",s.c_str());
}