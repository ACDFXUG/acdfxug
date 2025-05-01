#include <iostream>
#include <stack>

int main(){
    std::stack<const std::string> action;
    int n;
    std::cin>>n;
    while(n!=1){
        if(n&1){
            action.push("add");
            action.push("1");
            --n;
        }else{
            for(;(n&1)==0;n>>=1){
                action.push("add");
                action.push("dup");
            }
        }
    }
    action.push("1");
    for(;!action.empty();action.pop()){
        std::cout<<action.top()<<std::endl;
    }
}