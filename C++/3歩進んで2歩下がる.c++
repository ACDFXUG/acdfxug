#include <iostream>
#define Ull unsigned long long

int main(){
    Ull n;
    std::cin>>n;
    Ull step=n/2;
    printf("%llu\n",(n&1==1)?step+3ull:step);
}