#include <iostream>
#include <cmath>
#include <functional>
using ld=long double;

/// @param x 
/// @return f(x)的值
ld f(ld x){  //零点x0=0.2575302854
    return x*x-3*x+2-std::exp(x);
}

ld phi_f(ld x){
    return (2-std::exp(x))/(3-x);
}

ld der_f(ld x){
    return 2*x-3-std::exp(x);
}

/// @param x 
/// @return g(x)的值
ld g(const ld &x){  //零点x0=1.368808107821373
    return x*x*x+2*x*x+10*x-20;
}

ld phi_g(ld x){
    return 20/(x*x+2*x+10);
}

ld der_g(ld x){
    return 3*x*x+4*x+10;
}

class FixedPoint{
private:
    ld x0,eps;
    const std::function<ld(ld)> func;
    int iters;
public:
    FixedPoint(std::function<ld(ld)> &&fun,ld eps,ld x0=0):
    func(fun),eps(eps),x0(x0),iters(0){}
    void root(){
        ld xk=x0;
        for(;;){
            ld xkp=func(xk);
            printf("{ 不动点迭代次数: %2d, 迭代值: %.10Lf }\n",iters++,xk);
            if(std::abs(xkp-xk)<eps){
                printf("[ 最后的迭代次数: %d, 迭代值: %.10Lf ]\n\n",iters,xkp);
                return;
            }
            xk=xkp;
        }
    }
};

class Steffensen{
private:
    ld x0,eps;
    const std::function<ld(ld)> func;
    int iters;
public:
    Steffensen(std::function<ld(ld)> &&fun,ld eps,ld x0=0):
    func(fun),eps(eps),x0(x0),iters(0){}
    void root(){
        ld xk=x0;
        for(;;){
            ld x1=func(xk);
            ld x2=func(x1);
            ld x_new=x2-(x2-x1)*(x2-x1)/(x2-2*x1+xk);
            printf("{ 斯特芬森迭代次数: %2d, 迭代值: %.10Lf }\n",iters++,xk);
            if(std::abs(x_new-xk)<eps){
                printf("[ 最后的迭代次数: %d, 迭代值: %.10Lf ]\n\n",iters,x_new);
                return;
            }
            xk=x_new;
        }
    }
};

class Newton{
private:
    ld x0,eps;
    const std::function<ld(ld)> func,der;
    int iters;
public:
    Newton(std::function<ld(ld)> &&fun,std::function<ld(ld)> &&der,ld eps,ld x0=0):
    func(fun),der(der),eps(eps),x0(x0),iters(0){}
    void root(){
        ld xk=x0;
        for(;;){
            ld xkp=xk-func(xk)/der(xk);
            printf("{ 牛顿迭代次数: %2d, 迭代值: %.10Lf }\n",iters++,xk);
            if(std::abs(xkp-xk)<eps){
                printf("[ 最后的迭代次数: %d, 迭代值: %.10Lf ]\n\n",iters,xkp);
                return;
            }
            xk=xkp;
        }
    }
};

int main(){
    FixedPoint fp_f(phi_f,1e-8,0.25);
    printf("f(x)迭代初值: %Lf\n",0.25l);
    fp_f.root();

    FixedPoint fp_g(phi_g,1e-8,1.5);
    printf("g(x)迭代初值: %Lf\n",1.5l);
    fp_g.root();

    Steffensen sf_f(phi_f,1e-8,0.25);
    printf("f(x)迭代初值: %Lf\n",0.25l);
    sf_f.root();

    Steffensen sf_g(phi_g,1e-8,1.5);
    printf("g(x)迭代初值: %Lf\n",1.5l);
    sf_g.root();

    Newton nt_f(f,der_f,1e-8,0.25);
    printf("f(x)迭代初值: %Lf\n",0.25l);
    nt_f.root();

    Newton nt_g(g,der_g,1e-8,1.5);
    printf("g(x)迭代初值: %Lf\n",1.5l);
    nt_g.root();
}