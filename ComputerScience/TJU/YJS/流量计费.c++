#include <iostream>

int main(){
    int T;
    std::cin>>T;
    for(int m;T-->0;){
        std::cin>>m;
        int remain=500,price=10,used;
        while(m-->0){
            std::cin>>used;
            if(used<=remain){
                remain-=used;
            }else{
                price+=used-remain;
                remain=0;
            }
        }
        std::cout<<price<<std::endl;
    }
}