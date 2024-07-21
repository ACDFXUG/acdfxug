#include <iostream>
#include <string.h>
int main(){
    int b[10];
    memset(b,-1,sizeof(b));
    for(int X:b){
        std::cout<<X<<std::endl;
    }
}