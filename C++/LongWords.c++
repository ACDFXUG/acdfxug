#include <iostream>
#define String std::string

String shorten(String s){
    int l=s.length();
    return s[0]+std::to_string(l-2)+s[l-1];
}

int main(){
    int n;
    std::cin>>n;
    String s;
    for(int i=0;i<n;i++){
        std::cin>>s;
        printf("%s\n",s.length()<=10?s.c_str():shorten(s).c_str());
    }

}