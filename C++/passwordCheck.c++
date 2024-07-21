#include <iostream>
#define String std::string

bool Length(String s){
    return s.length()>=5;
}

bool hasDigits(String s){
    for(char x:s){
        if(x>='0'&&x<='9'){
            return true;
        }
    }
    return false;
}

bool hasUpper(String s){
    for(char x:s){
        if(x>='A'&&x<='Z'){
            return true;
        }
    }
    return false;
}

bool hasLower(String s){
    for(char x:s){
        if(x>='a'&&x<='z'){
            return true;
        }
    }
    return false;
}

int main(){
    String s;
    std::cin>>s;
    printf(Length(s)&&hasDigits(s)&&hasLower(s)&&hasUpper(s)?"Correct\n":"Too weak\n");
}