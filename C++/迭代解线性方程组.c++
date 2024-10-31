#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <random>
#include <iomanip>
#include <map>
using ld=long double;
using V=std::vector<ld>;
using Vv=std::vector<V>;

struct Matrix{
    Vv data;
    int row,col;
    Matrix():data(),row(1),col(1){}
    Matrix(int n,int m):
    data(n,V(m,0)),row(n),col(m){}
    Matrix(const Vv &mat):
    data(mat),row(mat.size()),col(mat[0].size()){}
    Matrix(const Vv &&mat):
    data(std::move(mat)),row(mat.size()),col(mat[0].size()){}
    Matrix(const Matrix &mat):
    data(mat.data),row(mat.row),col(mat.col){}
    Matrix &operator =(const Matrix &mat){
        data=mat.data;
        row=mat.row;
        col=mat.col;
        return *this;
    }
    auto &operator [](const int &r){
        return data[r];
    }
    auto &operator [](const int &r) const{
        return data[r];
    }
    int size() const{
        return data.size();
    }
    Matrix operator +(const Matrix &mat) const{
        Matrix ans(data);
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                ans[i][j]+=mat[i][j];
            }
        }
        return ans;
    }
    Matrix operator -(const Matrix &mat) const{
        Matrix ans(data);
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                ans[i][j]-=mat[i][j];
            }
        }
        return ans;
    }
    Matrix operator *(const Matrix &mat) const{
        Matrix ans(row,mat.col);
        int tmp=col;
        for(int i=0;i<row;i++){
            for(int j=0;j<mat.col;j++){
                for(int k=0;k<tmp;k++){
                    ans[i][j]+=data[i][k]*mat[k][j];
                }
            }
        }
        return ans;
    }
    friend Matrix operator *(ld k,const Matrix &mat){
        Matrix ans(mat);
        for(int i=0;i<mat.row;i++){
            for(int j=0;j<mat.col;j++){
                ans[i][j]*=k;
            }
        }
        return ans;
    }
    void printm(int decimal) const{
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                std::cout<<std::setprecision(decimal)
                <<data[i][j]<<(j==col-1?"\n":" ");
            }
        }
    }
};

struct GaussSeidel{
    Matrix A,b,x0;
    int max_iter;
    ld epsilon;
    Matrix D,U,L;
    int iters;
    GaussSeidel(Matrix A,Matrix b,Matrix x0,int max_iter,ld epsilon):
    A(A),b(b),x0(x0),max_iter(max_iter),epsilon(epsilon),iters(0){
        this->D=Matrix(A.row,A.col);
        this->U=Matrix(A.row,A.col);
        this->L=Matrix(A.row,A.col);
        for(int i=0;i<A.row;i++){
            for(int j=0;j<A.col;j++){
                if(i==j){
                    D[i][j]=A[i][j];
                }else if(i<j){
                    U[i][j]=-A[i][j];
                }else{
                    L[i][j]=-A[i][j];
                }
            }
        }
    }
    Matrix inverse() const{
        Matrix Low=D-L;
        Matrix E(A.row,A.col);
        for(int i=0;i<A.row;i++){
            E[i][i]=1;
        }
        Matrix X(A.row,A.col);
        for(int j=0;j<A.col;j++){
            for(int i=0;i<A.row;i++){
                if(i==0){
                    X[i][j]=E[i][j]/Low[i][i];
                }else{
                    ld sum=0;
                    for(int k=0;k<i;k++){
                        sum+=Low[i][k]*X[k][j];
                    }
                    X[i][j]=(E[i][j]-sum)/Low[i][i];
                }
            }
        }
        return X;
    }
    Matrix iteration(){
        auto x=x0;
        const auto X=inverse(), //X==(D-L)^(-1)
            G=X*U,g=X*b;
        for(int i=0;i<max_iter;i++){
            ld max=-1;
            auto r=b-A*x;
            for(int j=0;j<A.row;j++){
                max=std::max(max,fabsl(r[j][0]));
            }
            if(max<epsilon){
                return x;
            }
            x=G*x+g;
            iters++;
        }
        return x;
    }
};

struct SORIteration{
    Matrix A,b,x0;
    int max_iter;
    ld epsilon,omega;
    Matrix D,U,L,X;
    int iters;
    SORIteration(Matrix A,Matrix b,Matrix x0,int max_iter,ld epsilon,ld omega):
    A(A),b(b),x0(x0),max_iter(max_iter),epsilon(epsilon),omega(omega),iters(0){
        this->D=Matrix(A.row,A.col);
        this->U=Matrix(A.row,A.col);
        this->L=Matrix(A.row,A.col);
        for(int i=0;i<A.row;i++){
            for(int j=0;j<A.col;j++){
                if(i==j){
                    D[i][j]=A[i][j];
                }else if(i<j){
                    U[i][j]=-A[i][j];
                }else{
                    L[i][j]=-A[i][j];
                }
            }
        }
        X=inverse();//X==(D-ω*L)^(-1)
    }
    Matrix inverse() const{
        Matrix Low=D-omega*L;
        Matrix E(A.row,A.col);
        for(int i=0;i<A.row;i++){
            E[i][i]=1;
        }
        Matrix X(A.row,A.col);
        for(int j=0;j<A.col;j++){
            for(int i=0;i<A.row;i++){
                if(i==0){
                    X[i][j]=E[i][j]/Low[i][i];
                }else{
                    ld sum=0;
                    for(int k=0;k<i;k++){
                        sum+=Low[i][k]*X[k][j];
                    }
                    X[i][j]=(E[i][j]-sum)/Low[i][i];
                }
            }
        }
        return X;
    }
    Matrix iteration(){
        auto x=x0;
        iters=0;
        const auto S=X*((1-omega)*D+omega*U),s=omega*X*b;
        for(int i=0;i<max_iter;i++){
            ld max=-1;
            auto r=b-A*x;
            for(int j=0;j<A.row;j++){
                max=std::max(max,fabsl(r[j][0]));
            }
            if(max<epsilon){
                return x;
            }
            x=S*x+s;
            iters++;
        }
        return x;
    }
};

int main(){
    std::default_random_engine dre;
    dre.seed(time(NULL));
    int n,max_iter;
    ld epsilon,omega;
    printf("输入矩阵阶数:\n");
    scanf("%d",&n);
    // printf("\n输入最大迭代次数K,迭代精度ε,松弛参数ω:\n");
    // scanf("%d%Lf%Lf",&max_iter,&epsilon,&omega);
    Matrix A(n,n),b(n,1),x0(n,1);
    printf("系数矩阵A:\n");
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            A[i][j]=(dre()%9+1)/10.;
            printf(j==n-1?"%.2Lf\n":"%.2Lf ",A[i][j]);
        }
    }
    printf("\n常数向量b:\n");
    for(int i=0;i<n;i++){
        b[i][0]=(dre()%9+1)/10.;
        printf("%.2Lf\n",b[i][0]);
    }

    printf("\n高斯-塞德尔迭代法迭代结果:\n");
    GaussSeidel gs(A,b,x0,50,1e-6);
    auto g=gs.iteration();
    g.printm(9);
    printf("迭代次数:%d\n",gs.iters);

    printf("\nSOR迭代法迭代结果:\n");
    SORIteration sor(A,b,x0,50,1e-6,0.8);
    auto s=sor.iteration();
    s.printm(9);
    printf("迭代次数:%d\n",sor.iters);

    const Matrix A_certain={{
        {31, -13, 0, 0, 0, -10, 0, 0, 0},
        {-13, 35, -9, 0, -11, 0, 0, 0, 0},
        {0, -9, 31, -10, 0, 0, 0, 0, 0},
        {0, 0, -10, 79, -30, 0, 0, 0, -9},
        {0, 0, 0, -30, 57, -7, 0, -5, 0},
        {0, 0, 0, 0, -7, 47, -30, 0, 0},
        {0, 0, 0, 0, 0, -30, 41, 0, 0},
        {0, 0, 0, 0, -5, 0, 0, 27, -2},
        {0, 0, 0, -9, 0, 0, 0, -2, 29}
    }};
    const Matrix b_certain={{
        {-15},
        {27},
        {-23},
        {0},
        {-20},
        {12},
        {-7},
        {7},
        {10}
    }};
    Matrix x0_certain(9,1);
    GaussSeidel gs_certain(A_certain,b_certain,x0_certain,100,1e-8);
    auto g_certain=gs_certain.iteration();
    printf("\n指定高斯-塞德尔迭代法:\n");
    g_certain.printm(9);
    printf("\n迭代次数:%d\n",gs_certain.iters);

    SORIteration sor_certain(A_certain,b_certain,x0_certain,100,1e-8,1.1);
    auto s_certain=sor_certain.iteration();
    printf("\n指定SOR迭代法:\n");
    s_certain.printm(9);
    printf("\n迭代次数:%d\n",sor_certain.iters);

    printf("\n取SOR迭代法中松弛参数ω:\n");
    std::map<int,ld> iter;
    for(int i=1;i<=99;i++){
        SORIteration sori(A_certain,b_certain,x0_certain,100,1e-8,i/50.);
        sori.iteration();
        iter[sori.iters]=i/50.;
        printf("%d ",sori.iters);
    }                    
    auto &[min_iter,min_ω]=*iter.begin();
    printf("\n最好的松弛参数为:%Lf,此时迭代次数为:%d\n",min_ω,min_iter);
}