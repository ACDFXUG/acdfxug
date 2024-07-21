#include <iostream>

int sum(int t){
    return t+(41-t)+(40+t)+(81-t)+(80+t)+(121-t)+(120+t)+(161-t)+(160+t)+(201-t);
}

int main(){
    int t;
    std::cin>>t;
    std::cout<<sum(t)<<std::endl;
}