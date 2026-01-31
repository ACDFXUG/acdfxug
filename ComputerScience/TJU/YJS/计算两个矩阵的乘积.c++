#include <iostream>
#include <vector>

using Matrix=std::vector<std::vector<size_t>>;

Matrix operator *(const Matrix &a,const Matrix &b){
    Matrix c(a.size(),std::vector<size_t>(b[0].size()));
    for(size_t i=0;i<a.size();i++){
        for(size_t j=0;j<b[0].size();j++){
            for(size_t k=0;k<a[0].size();k++){
                c[i][j]+=a[i][k]*b[k][j];
            }
        }
    }
    return c;
}

int main(){
    Matrix a(2,std::vector<size_t>(3));
    Matrix b(3,std::vector<size_t>(2));
    for(size_t i=0;i<2;i++){
        for(size_t j=0;j<3;j++){
            std::cin>>a[i][j];
        }
    }
    for(size_t i=0;i<3;i++){
        for(size_t j=0;j<2;j++){
            std::cin>>b[i][j];
        }
    }
    Matrix c=a*b;
    for(size_t i=0;i<2;i++){
        for(size_t j=0;j<2;j++){
            std::cout<<c[i][j]<<" ";
        }
        std::cout<<std::endl;
    }
}