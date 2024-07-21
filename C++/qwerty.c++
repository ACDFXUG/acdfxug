#include <iostream>

int main(){
    for(int ch,i=0;i<26;i++){
        scanf("%d",&ch);
        printf("%c",'a'+ch-1);
    }
    std::cout<<std::endl;
}