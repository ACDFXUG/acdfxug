#include <iostream>
#include <array>

const std::array<int,5> FINAL{1,2,3,4,5};
void print(std::array<int,5> a){
    for(int i=0;i<5;i++){
        printf(i==4?"%d\n":"%d ",a[i]);
    }
}

int main(){
    std::array<int,5> a;
    for(int i=0;i<5;scanf("%d",&a[i++]));
    BACK:
    for(int i=0;i<4;i++){
        if(a[i]>a[i+1]){
            std::swap(a[i],a[i+1]);
            print(a);
        }
    }
    if(a!=FINAL){
        goto BACK;
    }
}