#include <iostream>
#include <algorithm>

int main(){
    std::string S,T="";
    std::cin>>S;
    for(char c:S){
        if(c=='R'){
            std::reverse(T.begin(),T.end());
        }else{
            int l=T.length();
            if(l==0){
                T+=c;
            }else{
                if(T[l-1]==c){
                    T.pop_back();
                }else{
                    T+=c;
                }
            }
        }
    }
    std::cout<<T<<std::endl;
}