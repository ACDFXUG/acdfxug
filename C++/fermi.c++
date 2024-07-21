#include <iostream>
#include <cmath>
#define input std::cin>>
#define output std::cout<<
#define endline <<std::endl
int Fermi(double x){
    int a=1;
    for(int i=1;i<=round(log10(x));i++){
        a*=10;
    }
    return a;
}

int main(){
    double x;
    input x;
    output Fermi(x) endline;
}


