#include <iostream>
#include <cmath>

int main(){
    long long n;
    std::cin>>n;
    for(long long k=(long long)(-1+sqrtl(1+8*n))/2-1;;k++){
        if(k*(k+1)/2>=n){
            printf("%d\n",k);
            break;
        }
    }
}