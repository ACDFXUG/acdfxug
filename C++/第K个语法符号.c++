#include <iostream>

int kthGrammar(int n, int k) {
    int res=0;
    for(;n>1;n--){
        int mid=(1<<(n-1))<<1;
        if(k>mid){
            k-=mid;
            res=!res;
        }
    }
    return res;
}

int main(){
    std::cout<<kthGrammar(2,1)<<std::endl;
}