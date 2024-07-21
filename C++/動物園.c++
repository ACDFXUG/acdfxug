#include <iostream>

int main(){
    int A,B,C,K,kid,adult;
    scanf("%d%d%d%d%d%d",&A,&B,&C,&K,&kid,&adult);
    int group=kid+adult;
    if(group>=K){
        printf("%d\n",(A-C)*kid+(B-C)*adult);
    }else{
        printf("%d\n",A*kid+B*adult);
    }
}