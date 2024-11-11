#include <iostream>
#include <cmath>
#include <functional>
using ld=long double;

ld f(ld x){  //零点x0=0.0905251013
    return std::exp(x)+10*x-2;
}

const ld zero=0.0905251013l;

ld phi_f(ld x){
    return 0.2-std::exp(x)/10;
}

class Bisection{
private:
    ld itvl,itvr,eps;
    const std::function<ld(ld)> func;
    int iters;
public:
    Bisection(std::function<ld(ld)> &&fun,ld eps,ld l,ld r):
    func(fun),eps(eps),itvl(l),itvr(r),iters(0){}
    void root(){
        ld ak=itvl,bk=itvr,xk=(ak+bk)/2;
        if(func(ak)<0&&func(bk)>0){
            for(;;){
                ld fxk=func(xk);
                printf("{ iters=%d,ak=%Lf,bk=%Lf,xk=%.10Lf,f(xk)=%Lf }\n",
                iters++,ak,bk,xk,fxk);
                if(fxk>0){
                    bk=xk;
                }else{
                    ak=xk;
                }
                if(std::abs(xk-zero)<eps){
                    printf("{ iters=%d,ak=%Lf,bk=%Lf,xk=%.10Lf,f(xk)=%Lf }\n\n",
                    iters,ak,bk,xk,fxk);
                    return;
                }
                xk=(ak+bk)/2;
            }
        }else{
            for(;;){
                ld fxk=func(xk);
                printf("{ iters=%d,ak=%Lf,bk=%Lf,xk=%.10Lf,f(xk)=%Lf }\n",
                iters++,ak,bk,xk,fxk);
                if(fxk<0){
                    bk=xk;
                }else{
                    ak=xk;
                }
                if(std::abs(xk-zero)<eps){
                    printf("{ iters=%d,ak=%Lf,bk=%Lf,xk=%.10Lf,f(xk)=%Lf }\n\n",
                    iters,ak,bk,xk,fxk);
                    return;
                }
                xk=(ak+bk)/2;
            }
        }
    }
};

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

int main(){
    Bisection bisection(f,1e-4,0,1);
    bisection.root();

    FixedPoint fixedpoint(phi_f,1e-4);
    fixedpoint.root();
}