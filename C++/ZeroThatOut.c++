#include <iostream>
#include <stack>

int main(){
    int k,sum=0;
    std::cin>>k;
    std::stack<int> zto;
    for(int x;k-->0;){
        scanf("%d",&x);
        switch(x){
            case 0:zto.pop();break;
            default:zto.push(x);break;
        }
    }
    for(;!zto.empty();sum+=zto.top(),zto.pop());
    printf("%d",sum);
}