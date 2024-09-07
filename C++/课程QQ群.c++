#include <iostream>

int main(){
    int n,k,cnt=0;
    std::cin>>n>>k;
    for(int x;n-->0;cnt+=(x==k)){
        scanf("%d",&x);
    }
    std::cout<<cnt<<std::endl;
}