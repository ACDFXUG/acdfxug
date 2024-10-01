#include <iostream>

int Pascal(int N){
    int cntr=0;
    for(int i=N/2;i>=1;i--){
        cntr++;
        if(N%i==0){
            break;
        }
    }
    return cntr;
}

int main(){
    int n;
    std::cin>>n;
    printf("%d\n",Pascal(n));
}