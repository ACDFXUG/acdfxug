#include <iostream>
#include <cmath>
#include <algorithm>
#include <vector>
#include <functional>
typedef long double ld;
#define println(x) std::cout<<(x)<<std::endl
#define MATRIX std::vector<std::vector<ld>>     

ld a,b; //区间
ld c;  //函数控制参数
int k;  //多项式次数(k=1,2,3)
int n,m;  //采样点与实验点个数
ld h_n;  //采样点步长
ld h_m;  //实验点步长

ld func(ld x){
    return 1/(1+c*x*x);
}

auto x_points(){
    std::vector<ld> X(n+1);
    for(int i=0;i<=n;i++){
        X[i]=a+i*h_n;
    }
    return X;
}

auto y_points(){
    std::vector<ld> Y(n+1),X=x_points();
    for(int i=0;i<=n;i++){
        Y[i]=func(X[i]);
    }
    return Y;
}

auto x_exp_points(){
    std::vector<ld> X(m+1);
    for(int i=0;i<=m;i++){
        X[i]=a+i*h_m;
    }
    return X;
}

auto y_exp_points(){
    std::vector<ld> Y(m+1),X=x_exp_points();
    for(int i=0;i<=m;i++){
        Y[i]=func(X[i]);
    }
    return Y;
}

/**
 * 函数积分计算
 * 使用辛普森法则（Simpson's rule）来近似计算函数f在区间[l, r]上的积分
 * 辛普森法则通过拟合二次曲线来提高积分的精度
 * 
 * @param f 积分的函数
 * @param l 积分区间的下界，默认为-1
 * @param r 积分区间的上界，默认为1
 * @param N 区间分割数，即在积分区间内取样的点数，默认为1000
 * @return 返回函数f在区间[l, r]上的积分近似值
 */
ld integrate(const std::function<ld(ld)> &&f,ld l=-1,ld r=1,size_t N=1000uz) {
    // 如果分割数N为奇数，将其加一使之成为偶数
    // 原因是辛普森法则要求区间分割数为偶数以保证计算的对称性
    if ((N&1)==1) ++N;
    // 计算积分区间的步长h，即区间长度除以分割数
    ld h=(r-l)/N;
    // 初始化积分和inte为0
    ld inte=.0l;
    // 遍历每个小区间，应用辛普森法则计算积分
    for(int i=0;i<N;i++){
        // 计算当前小区间的左边界xi
        ld xi=l+i*h;
        // 根据辛普森法则，累加当前小区间的积分贡献
        inte+=h*(f(xi)+4*f(xi+h/2)+f(xi+h));
    }
    // 返回积分结果
    return inte/6;
}


//勒让德多项式平方逼近
class Legendre{
private:
    const std::vector<ld> aa;
    static ld P(int k,ld t){
        switch(k){
            case 0: return 1;
            case 1: return t;
            case 2: return 1.5*t*t-0.5;
            case 3: return 2.5*t*t*t-1.5*t;
            default:throw;
        }
    }
    static ld g(ld t){
        return func(((a+b)/2)+(t*(b-a)/2));
    }
    static auto scalar(){
        std::vector<ld> ak;
        for(int i=0;i<=k;i++){
            ak.push_back((i+0.5)*integrate([&i](ld T){
                return P(i,T)*g(T);
            }));
        }
        return ak;
    }
    ld M(ld t) const{
        ld ans=0;
        for(int i=0;i<=k;i++){
            ans+=aa[i]*P(i,t);
        }
        return ans;
    }
public:
    Legendre():aa(scalar()){}
    ld Sk(ld x) const{
        return M((2/(b-a))*(x-((a+b)/2)));
    }
};

// 最小二乘逼近
class LeastSquares{
private:
    const std::vector<ld> X,Y,aa;
    static ld phi(int k,ld x){
        ld ans=1.l;
        for(;k;k>>=1){
            if(k&1){
                ans*=x;
            }
            x*=x;
        }
        return ans;
    }
    static ld determine(const MATRIX &mat){
        int p=mat.size();
        if(p==1){
            return mat[0][0];
        }
        if(p==2){
            return mat[0][0]*mat[1][1]-mat[0][1]*mat[1][0];
        }
        ld det=.0l;
        MATRIX minor(p-1,std::vector<ld>(p-1,0));
        for(int j=0;j<p;j++){
            for(int i=1;i<p;i++){
                int col=0;
                for(int k=0;k<p;k++){
                    if(k!=j){
                        minor[i-1][col++]=mat[i][k];
                    }
                }
            }
            det+=((j&1)?-1:1)*mat[0][j]*determine(minor);
        }
        return det;
    }
    MATRIX grim_mat() const{
        MATRIX mat(k+1,std::vector<ld>(k+1,0));
        for(int i=0;i<=k;i++){
            for(int j=0;j<=k;j++){
                for(int s=0;s<=n;s++){
                    mat[j][i]+=phi(i,X[s])*phi(j,X[s]);
                }
            }
        }
        return mat;
    }
    auto B() const{
        std::vector<ld> bk(k+1,0);
        for(int i=0;i<=k;i++){
            for(int s=0;s<=n;s++){
                bk[i]+=Y[s]*phi(i,X[s]);
            }
        }
        return bk;
    }
    auto scalar() const{
        std::vector<ld> ak(k+1,0);
        auto mat=grim_mat();
        auto yk=B();
        ld det=determine(mat);
        for(int i=0;i<=k;i++){
            auto tmp=mat[i];
            mat[i]=yk;
            ak[i]=determine(mat)/det;
            mat[i]=tmp;
        }
        return ak;
    }
public:
    LeastSquares(
        const std::vector<ld> &x,
        const std::vector<ld> &y
    ):X(x),Y(y),aa(scalar()){}
    ld Sk(ld x) const{
        ld ans=0;
        for(int i=0;i<=k;i++){
            ans+=aa[i]*phi(i,x);
        }
        return ans;
    }
};


void printv(const std::vector<ld> &v){
    for(int i=0,l=v.size();i<l;i++){
        std::cout<<v[i]<<(i==l-1?"\n":" ");
    }
}

int main(){
    println("输入区间端点a,b和函数控制参数c:");
    std::cin>>a>>b>>c;
    println("输入多项式次数k:");
    std::cin>>k;
    println("输入采样点数n和实验点数m:");
    std::cin>>n>>m;
    h_n=(b-a)/n;
    h_m=(b-a)/m;
    auto Xp=x_points(),Yp=y_points();
    auto X_exp=x_exp_points(),
        Y_exp=y_exp_points();
    println("实验点的x值:");
    printv(X_exp);
    println("实验点的y值:");
    printv(Y_exp);

    printf("\nLegendre多项式逼近:\n");
    Legendre leg;
    std::vector<ld> lgd(m+1);
    ld err1=.0l;
    for(int i=0;i<=m;i++){
        lgd[i]=leg.Sk(X_exp[i]);
        err1+=(lgd[i]-Y_exp[i])*(lgd[i]-Y_exp[i]);
    }
    printf("拟合后的y值:\n    ");
    printv(lgd);
    printf("误差平方和为: %Lf\n",err1);

    printf("\nLeast Squares逼近:\n");
    LeastSquares lsq(Xp,Yp);
    std::vector<ld> lsqd(m+1);
    ld err2=.0l;
    for(int i=0;i<=m;i++){
        lsqd[i]=lsq.Sk(X_exp[i]);
        err2+=(lsqd[i]-Y_exp[i])*(lsqd[i]-Y_exp[i]);
    }
    printf("拟合后的y值:\n    ");
    printv(lsqd);
    printf("误差平方和为: %Lf\n",err2);
}