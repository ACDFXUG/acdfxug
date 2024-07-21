#include <iostream>
#define Ull unsigned long long

Ull AA[]={1,4,27,256,3125,46656,823543,
16777216,387420489,10000000000,285311670611,
8916100448256,302875106592253,11112006825558016,
437893890380859375};

int contains(Ull B){
    for(int i=0;i<15;i++){
        if(B==AA[i]){
            return i+1;
        }
    }
    return -1;
}

int main(){
    Ull B;
    std::cin>>B;
    printf("%d\n",contains(B));
}