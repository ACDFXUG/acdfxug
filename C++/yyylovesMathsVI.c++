#include <iostream>

#define FAST_IO(boo1,nul1,nul2) do{\
    std::ios::sync_with_stdio(boo1);\
    std::cin.tie(nul1);\
    std::cout.tie(nul2);\
}while(0)

int main(){
    FAST_IO(false,NULL,NULL);
    int n,x,count=0;
    int mode=0;
    std::cin>>n;
    while(n-->0){
        std::cin>>x;
        count+=mode==x?1:-1;
        if(count<0) count=0;
        else if(count==0)[[unlikely]]{
            mode=x;
            count=1;
        }
    }
    std::cout<<mode<<std::endl;
}