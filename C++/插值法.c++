#include <iostream>
#include <cmath>
#include <vector>
#define MATRIX std::vector<std::vector<ld>> 
typedef long double ld;

ld a,b; // 区间
ld c,d,e,f;  // 函数参数
int n,m;  // 采样点数和实验点数
/**
 * @brief 用于插值的原函数
 * @return 函数值F(x)
 */
const ld F(ld x){
    return c*std::sin(d*x)+e*std::cos(f*x);
}
/**
 * @brief 函数的导数
 * @return 所求函数的导数值
 */
const ld deri_func(ld x){
    return c*d*std::cos(d*x)-e*f*std::sin(f*x);
}
/**
 * @brief 
 * @return 采样点x值
 */
const auto x_points(){
    std::vector<ld> ans;
    for(int i=0;i<=n;i++){
        ans.push_back(a+i*(b-a)/n);
    }
    return ans;
}
/**
 * @brief 
 * @return 实验点x值
 */
const auto exp_x_points(){
    std::vector<ld> ans;
    for(int i=0;i<=m;i++){
        ans.push_back(a+i*(b-a)/m);
    }
    return ans;
}
/**
 * @brief 
 * @return 采样点y值
 */
const auto y_points(){
    std::vector<ld> ans,x=x_points();
    for(int i=0;i<=n;i++){
        ans.push_back(F(x[i]));
    }
    return ans;
}
/**
 * @brief 
 * @return 实验点y值
 */
const auto exp_y_points(){
    std::vector<ld> ans,x=exp_x_points();
    for(int i=0;i<=m;i++){
        ans.push_back(F(x[i]));
    }
    return ans;
}

//范德蒙德插值
class Vandermonde{
private:
    const std::vector<ld> X,Y,coeff;
    static int negative(const int p){
        return (p&1)?-1:1;
    }
    static ld fast_pow(ld x,int s){
        ld ans=1.l;
        for(;s>0;s>>=1){
            if(s&1){
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
            det+=negative(j)*mat[0][j]*determine(minor);
        }
        return det;
    }
    static std::vector<ld> coefficient(
        const std::vector<ld> &x,
        const std::vector<ld> &y){
        std::vector<ld> ans;
        MATRIX mat(n+1,std::vector<ld>(n+1));
        for(int i=0;i<=n;i++){
            for(int j=0;j<=n;j++){
                mat[i][j]=fast_pow(x[j],i);
            }
        }
        ld deter=determine(mat);
        for(int i=0;i<=n;i++){
            auto tmp=mat[i];
            mat[i]=y;
            ans.push_back(determine(mat)/deter);
            mat[i]=tmp;
        }
        return ans;
    }
public:
    Vandermonde(std::vector<ld> &x,std::vector<ld> &y):
    X(x),Y(y),coeff(coefficient(x,y)){}
    // 范德蒙德插值函数
    ld L(ld x) const{
        ld ans=.0l;
        for(int i=0;i<=n;i++){
            ans+=coeff[i]*fast_pow(x,i);
        }
        return ans;
    }
};

//拉格朗日插值
class Lagrange{
private:
    const std::vector<ld> X,Y;
    ld lk(ld x,int k) const{  //拉格朗日基函数
        ld ans=1.l;
        for(int i=0;i<=n;i++){
            if(i!=k){
                ans*=(x-X[i])/(X[k]-X[i]);
            }
        }
        return ans;
    }
public:
    Lagrange(std::vector<ld> &x,std::vector<ld> &y):X(x),Y(y){}
    // 拉格朗日插值函数
    ld L(ld x) const{
        ld ans=.0l;
        for(int k=0;k<=n;k++){
            ans+=Y[k]*lk(x,k);
        }
        return ans;
    }
};

//牛顿差商插值
class Newton{
private:
    const std::vector<ld> X,Y;
    ld omegaK(ld x,int k) const{
        ld ans=1.l;
        for(int j=0;j<k;j++){
            ans*=(x-X[j]);
        }
        return ans;
    }
    ld difference(int k) const{  //差商
        ld sum=.0l;
        for(int i=0;i<=k;i++){
            ld tmp=1.l;
            for(int j=0;j<=k;j++){
                if(j!=i){
                    tmp*=(X[i]-X[j]);
                }
            }
            sum+=Y[i]/tmp;
        }
        return sum;
    }
    
public:
    Newton(std::vector<ld> &x,std::vector<ld> &y):X(x),Y(y){}
    // 牛顿插值函数
    ld L(ld x) const{
        ld ans=Y[0];
        for(int k=1;k<=n;k++){
            ans+=difference(k)*omegaK(x,k);
        }
        return ans;
    }
};

//分段线性插值
class Linear{
private:
    const std::vector<ld> X,Y;
public:
    Linear(std::vector<ld> &x,std::vector<ld> &y):X(x),Y(y){}
    // 线性插值函数
    ld L(ld x) const{
        int k=int(n*(x-a)/(b-a));
        k=std::min(k,n-1);
        return Y[k]+(Y[k+1]-Y[k])*(x-X[k])/(X[k+1]-X[k]);
    }
};

//埃尔米特插值
class Hermite{
private:
    const std::vector<ld> X,Y;
    ld alphaK(ld x,int k) const{
        return (1+2*(x-X[k])/(X[k+1]-X[k]))*
        ((x-X[k+1])/(X[k]-X[k+1]))*((x-X[k+1])/(X[k]-X[k+1]));
    }
    ld alphaKp(ld x,int k) const{
        return (1+2*(x-X[k+1])/(X[k]-X[k+1]))*
        ((x-X[k])/(X[k+1]-X[k]))*((x-X[k])/(X[k+1]-X[k]));
    }
    ld betaK(ld x,int k) const{
        return (x-X[k])*((x-X[k+1])/(X[k]-X[k+1]))
        *((x-X[k+1])/(X[k]-X[k+1]));
    }
    ld betaKp(ld x,int k) const{
        return (x-X[k+1])*((x-X[k])/(X[k+1]-X[k]))
        *((x-X[k])/(X[k+1]-X[k]));
    }
public:
    Hermite(std::vector<ld> &x,std::vector<ld> &y):X(x),Y(y){}
    // 埃尔米特插值函数
    ld L(ld x) const{
        int k=int(n*(x-a)/(b-a));
        k=std::min(k,n-1);
        ld mk=deri_func(X[k]);
        ld mkp=deri_func(X[k+1]);
        return Y[k]*alphaK(x,k)+Y[k+1]*alphaKp(x,k)
        +mk*betaK(x,k)+mkp*betaKp(x,k);
    }
};
/**
 * @brief 输出vector
 * @param v 要输出的vector
 */
void print(const std::vector<ld> &v){
    for(auto &x:v){
        std::cout<<x<<" ";
    }
    std::cout<<std::endl;
}

void print_avg_error(const std::vector<ld> &v1,const std::vector<ld> &v2){
    ld ans=.0l;
    for(int i=0;i<=m;i++){
        ans+=std::abs(v1[i]-v2[i]);
    }
    std::cout<<"平均误差为:"<<ans/(m+1)<<std::endl;
}

int main(){
    printf("输入a,b\n");
    std::cin>>a>>b;
    printf("输入c,d,e,f\n");
    std::cin>>c>>d>>e>>f;
    printf("输入n,m\n");
    std::cin>>n>>m;
    auto x=x_points(),y=y_points();
    auto exp_x=exp_x_points(),
    exp_y=exp_y_points();
    printf("测试的x坐标为:\n");
    print(exp_x);
    printf("测试的y坐标为:\n");
    print(exp_y);

    std::cout<<"\nVandermonde:\n";
    Vandermonde vdmd(x,y);
    std::vector<ld> vd_y(m+1);
    for(int i=0;i<=m;i++){
        vd_y[i]=vdmd.L(exp_x[i]);
    }
    print(vd_y);
    print_avg_error(exp_y,vd_y);

    std::cout<<"\nLagrange:\n";
    Lagrange lag(x,y);
    std::vector<ld> lag_y(m+1);
    for(int i=0;i<=m;i++){
        lag_y[i]=lag.L(exp_x[i]);
    }
    print(lag_y);
    print_avg_error(exp_y,lag_y);

    std::cout<<"\nNewton:\n";
    Newton nt(x,y);
    std::vector<ld> nt_y(m+1);
    for(int i=0;i<=m;i++){
        nt_y[i]=nt.L(exp_x[i]);
    }
    print(nt_y);
    print_avg_error(exp_y,nt_y);

    std::cout<<"\nLinear:\n";
    Linear lin(x,y);
    std::vector<ld> lin_y(m+1);
    for(int i=0;i<=m;i++){
        lin_y[i]=lin.L(exp_x[i]);
    }
    print(lin_y);
    print_avg_error(exp_y,lin_y);

    std::cout<<"\nHermite:\n";
    Hermite hrm(x,y);
    std::vector<ld> hrm_y(m+1);
    for(int i=0;i<=m;i++){
        hrm_y[i]=hrm.L(exp_x[i]);
    }
    print(hrm_y);
    print_avg_error(exp_y,hrm_y);
}