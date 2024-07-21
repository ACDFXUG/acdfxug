#include <iostream>

int main(){
    int n,k,t;
    std::cin>>n>>k;
    for(t=n;n>=k;t+=n/k,n=n/k+n%k);
    std::cout<<t<<std::endl;
}