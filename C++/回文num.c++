#include <iostream>
#define String std::string
#define toString(i) std::to_string(i)

bool isPalindrom(String num){
    for(int i=0;i<2;i++){
        if(num[i]!=num[4-i]){
            return false;
        }
    }
    return true;
}

int main(){
    int A,B,t=0;
    String x;
    std::cin>>A>>B;
    for(int i=A;i<=B;i++){
        x=toString(i);
        if(isPalindrom(x)){
            t++;
        }
    }
    printf("%d\n",t);
}