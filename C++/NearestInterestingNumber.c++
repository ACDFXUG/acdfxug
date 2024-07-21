#include <iostream>

int digitsum(int n){
    int s=0;
    for(char x:std::to_string(n)){
        s+=x-'0';
    }
    return s;
}

int main(){
    int a;
    scanf("%d",&a);
    for(int i=a;;i++){
        if(digitsum(i)%4==0){
            printf("%d",i);
            break;
        }
    }
}