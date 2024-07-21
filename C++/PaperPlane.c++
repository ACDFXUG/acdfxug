#include <iostream>
#include <cmath>

int main(){
    double k,n,s,p;
    std::cin>>k>>n>>s>>p;
    int avgpaper=ceil(n/s);
    int all=k*avgpaper;
    printf("%.0f\n",ceil(all/p));
}