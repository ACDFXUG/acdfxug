#include <iostream>

int main(){
    int n,ans=0;
    std::cin>>n;
    for(int x;n-->0;ans^=x){
        std::cin>>x;
    }
    std::cout<<ans<<std::endl;
}