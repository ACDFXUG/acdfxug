#include <iostream>
#define String std::string

bool isSame(String n){
    char x=n[0];
    for(int i=1;i<4;i++){
        if(x!=n[i]){
            return false;
        }
    }
    return true;
}

int main(){
    String n;
    std::cin>>n;
    printf(isSame(n)?"SAME\n":"DIFFERENT\n");
}