#include <iostream>

int integerBreak(int n) {
    if(n == 2) return 1;
    if(n == 3) return 2;
    int res = 1;
    while(n > 4){
        res *= 3;
        n -= 3;
    }
    return res * n;
}

int main(){
    std::cout << integerBreak(10) << std::endl;
}