#include <iostream>
#include "Matrix.h"
#define println(x) std::cout<< x <<std::endl;
int main(){
    int rows,cols;
    double x;
    std::cin>>rows>>cols;
    Matrix a(rows,cols);
    for(int i=0;i<rows;i++){
        for(int j=0;j<cols;j++){
            scanf("%lf",&x);
            a.nextValue(x,i,j);
        }
    } 
    println(a(0,0));
}