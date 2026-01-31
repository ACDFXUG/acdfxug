#include <iostream>
#include <string>
#include <cstring>
using std::string;

class MyString{
    const char* str;
    public:
    MyString(const char* s){
        this->str = s;
    }
    int length(){
        return strlen(str);
    }
    int find(const char* s){
        if(strstr(str,s)!=NULL){
            return strstr(str,s)-str;
        }else{
            return -1;
        }
    }
};

int main(){
    string s;
    std::cin >> s;
    MyString ms(s.c_str());
    int N;
    std::cin >> N;
    for(int i = 0; i < N; i++){
        int act;
        scanf("%d",&act);
        if(act==1){
            printf("%d\n",ms.length());
        }else if(act==2){
            char s[100];
            scanf("%s",s);
            printf("%d\n",ms.find(s));
        }
    }
}
