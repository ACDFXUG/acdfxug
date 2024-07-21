#include <iostream>

bool DUAL(std::string S){
    int L=S.length();
    for(int i=0;i<L/2;i++){
        if(S[i]!=S[L-i-1]){
            return false;
        }
    }
    return true;
}

int main(){
    std::string S;
    std::cin>>S;
    printf(DUAL(S)?"yes":"no");
}