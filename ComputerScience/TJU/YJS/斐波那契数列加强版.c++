#include <iostream>
#include <vector>
#define MOD 1000000007

using Matrix=std::vector<std::vector<size_t>>;

Matrix operator *(const Matrix &a,const Matrix &b){
    Matrix c(a.size(),std::vector<size_t>(b[0].size()));
    for(size_t i=0;i<a.size();i++){
        for(size_t j=0;j<b[0].size();j++){
            for(size_t k=0;k<a[0].size();k++){
                c[i][j]=(c[i][j]+a[i][k]*b[k][j])%MOD;
            }
        }
    }
    return c;
}

Matrix operator ^(Matrix a,size_t n){
    Matrix res(a.size(),std::vector<size_t>(a.size()));
    for(size_t i=0;i<a.size();i++){
        res[i][i]=1;
    }
    while(n){
        if(n&1){
            res=res*a;
        }
        a=a*a;
        n>>=1;
    }
    return res;
}

size_t fibo(int n){
    if(n<=1) return n;
    Matrix a{
        {1,1},
        {1,0}
    };
    return (a^(n-1))[0][0];
}
int main(){
    int n;
    std::cin>>n;
    std::cout<<fibo(n)<<std::endl;
}