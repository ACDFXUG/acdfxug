#include <iostream>
#include <cmath>

double harmony(int n){
    return n==1?1:harmony(n-1)+1.0/n;
}

int main(){
    int k,i;
    std::cin>>k;
    double E=exp(k);
    for(int i=(int)E;harmony(i)<=k;i++);
    printf("%d\n",i);
}