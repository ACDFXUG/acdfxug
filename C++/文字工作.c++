#include <iostream>

int main(){
    int n,sum=0;
    for(std::cin>>n;n>1;++sum){
        if(n&1){
            n--;
        }else{
            n>>=1;
        }
    }
    printf("%d\n",sum);
}