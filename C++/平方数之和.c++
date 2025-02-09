#include <iostream>
#include <cmath>

long squareSum(const long &a,const long &b){
    return a*a+b*b;
}

bool judgeSquareSum(int c) {
    long start=0,end=long(std::sqrt(c));
    auto C=static_cast<long>(c);
    for(;start<=end;){
        if(squareSum(start,end)==C) return true;
        else if(squareSum(start,end)>C) --end;
        else ++start;
    }
    return false;
}

int main(){
    std::cout<<judgeSquareSum(2147482647);
}