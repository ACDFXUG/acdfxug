#include <iostream>

int main(){
    char a[4][4];
    for(int i=0;i<4;i++){
        for(int j=0;j<4;j++){
            std::cin>>a[i][j];
        }
    }
    for(int i=3;i>=0;i--){
        for(int j=3;j>=0;j--){
            printf((!j)?"%c\n":"%c ",a[i][j]);
        }
    }
}