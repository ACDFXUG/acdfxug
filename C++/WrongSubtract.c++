#include <iostream>

char endIs(int x){
    std::string s=std::to_string(x);
    return s[s.length()-1];
}

int main(){
    int n,k;
    for(std::cin>>n>>k;k--;){
        if(endIs(n)!='0'){
            n--;
        }else{
            n/=10;
        }
    }
    printf("%d\n",n);
}