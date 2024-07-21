#include <iostream>
#define Ull unsigned long long

int main(){
    Ull N;
    scanf("%llu",&N);
    for(int k=0;k<64;k++){
        if((1Ull<<k)<=N&&(1Ull<<(k+1))>N){
            printf("%d\n",k);
            break;
        }
    }
}