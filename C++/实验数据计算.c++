#include <iostream>
#define output std::cout<<
#define input std::cin>>
#define endline <<std::endl
#define pi 3.1415926
int main(){
    double Tx;
    input Tx;
    output (0.03536*Tx*Tx/(4*pi*pi)-0.004297)*100 endline;
    output 100*(0.0000809+0.477*0.25*0.25) endline;
}