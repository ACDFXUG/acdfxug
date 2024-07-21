#include <iostream>
#include <stdint.h>
#include <x86intrin.h>
#define Uint unsigned int

const float a[5]={1,2,3,4,5};
const float b[5]={5,4,3,2,1};

float vector_dot(Uint len,const float *a,const float *b){
    double sum=0.0;
    Uint i=0u,end=len&(~3);
    for(;i<len;i+=4){
        __m128 A=_mm_load_ps(a+i);
        __m128 B=_mm_load_ps(b+i);
        __m128 C=_mm_dp_ps(A,B,0xF1);
        sum+=C[0];
    }
    for(;i<len;i++){
        sum+=a[i]*b[i];
    }
    return (float)sum;
}

int main(){
    std::cout<< vector_dot(5,a,b) <<std::endl;
}

