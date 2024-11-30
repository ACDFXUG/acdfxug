#include <iostream>
#include <print>

int main(){
    int N,A,B,loc=0,t;
    scanf("%d%d%d",&N,&A,&B);
    for(std::string s;N-->0;){
        std::cin>>s>>t;
        bool f=s=="West";
        if(t<A){
            loc+=f?-A:A;
        }else if(A<=t&&t<=B){
            loc+=f?-t:t;
        }else{
            loc+=f?-B:B;
        }
    }
    if(loc<0){
        std::println("West {}",-loc);
    }else if(loc>0){
        std::println("East {}",loc);
    }else{
        std::println("0");
    }
}