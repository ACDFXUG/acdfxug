#include <iostream>
#define String std::string

int main(){
    int N;String S,T="";
    std::cin>>N>>S;
    for(int i=0;i<N;i++){
        T+=String(2,S[i]);
    }
    std::cout<<T<<std::endl;
}