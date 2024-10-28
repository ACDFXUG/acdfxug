#include <iostream>
#include <cmath>
#include <vector>
#include <algorithm>
#include <random>
using ld=long double;
using V=std::vector<ld>;
using Vv=std::vector<V>;

struct Matrix{
    Vv data;
    Matrix():data(){}
    Matrix(int n,int m):
    data(n,V(m,0)){}
    Matrix(const Vv &mat):data(mat){}
    Matrix(Vv &&mat):data(std::move(mat)){}
    Matrix(const Matrix &mat):data(mat.data){}
    Matrix &operator =(const Matrix &mat){
        this->data=mat.data;
        return *this;
    }
    auto &operator [](const int &row){
        return data[row];
    }
    auto &operator [](const int &row) const{
        return data[row];
    }
    int size() const{
        return data.size();
    }
    template<typename... VEC>
    static void swap_row(int i,int j,VEC ...vecs){
        if(i==j) return;
        for(Matrix vec:{vecs...}){
            std::swap(vec[i],vec[j]);
        }
    }
};

//列主元消元
struct MaxColPivoting{
    Matrix A,b;
    MaxColPivoting(Matrix &A,Matrix &b):A(A),b(b){}
    V solution(){
        int n=A.size();
        V ans(n);
        for(int k=0;k<n-1;k++){
            int max_row=k;
            ld max_val=fabsl(A[k][k]);
            for(int i=k+1;i<n;i++){
                ld val=fabsl(A[i][k]);
                if(val>max_val){
                    max_row=i;
                    max_val=val;
                }
            }
            if(max_val<1e-6){
                std::cerr<<"列主元为0,无解"<<std::endl;
                exit(-1);
            }
            Matrix::swap_row(k,max_row,A,b);
            for(int i=k+1;i<n;i++){
                ld factor=A[i][k]/A[k][k];
                for(int j=k;j<n;j++){
                    A[i][j]-=factor*A[k][j];
                }
                b[i][0]-=factor*b[k][0];
            }
        }
        for(int i=n-1;i>=0;i--){
            ld sum=0;
            for(int j=i+1;j<n;j++){
                sum+=A[i][j]*ans[j];
            }
            if(fabsl(A[i][i])<1e-6){
                std::cerr<<"对角线元素为0,无解"<<std::endl;
                exit(-1);
            }
            ans[i]=(b[i][0]-sum)/A[i][i];
        }
        return ans;
    }
};

//LU分解法
struct LUDecomp{
    Matrix A,L,U,b;
    LUDecomp(Matrix &A,Matrix &b):A(A),b(b){
        get_LU();
    }
    void get_LU(){
        int n=A.size();
        L=Matrix(n,n);
        U=Matrix(n,n);
        for(int i=0;i<n;i++){
            for(int k=i;k<n;k++){//计算U
                ld sum=0;
                for(int j=0;j<i;j++){
                    sum+=L[i][j]*U[j][k];
                }
                U[i][k]=A[i][k]-sum;
            }

            for(int k=i;k<n;k++){//计算L
                if(i==k){
                    L[i][i]=1;
                }else{
                    ld sum=0;
                    for(int j=0;j<i;j++){
                        sum+=L[k][j]*U[j][i];
                    }
                    L[k][i]=(A[k][i]-sum)/U[i][i];
                }
            }
        }
        for(int i=0;i<n;i++){
            if(fabsl(U[i][i])<1e-6){
                std::cerr<<"U主对角线元素为0,无解"<<std::endl;
                exit(-1);
            }
        }
    }

    auto forward(){
        int n=b.size();
        V y(n,0);
        for(int i=0;i<n;i++){
            ld sum=0;
            for(int j=0;j<i;j++){
                sum+=L[i][j]*y[j];
            }
            y[i]=b[i][0]-sum;
        }
        return y;
    }

    auto backward(V &&y){
        int n=b.size();
        V x(n,0);
        for(int i=n-1;i>=0;i--){
            ld sum=0;
            for(int j=i+1;j<n;j++){
                sum+=U[i][j]*x[j];
            }
            x[i]=(y[i]-sum)/U[i][i];
        }
        return x;
    }

    V solution(){
        return backward(forward());
    }
};

void printv(const V &vec){
    for(auto &val:vec){
        std::cout<<val<<" ";
    }
}

void printvln(const V &vec){
    for(auto &val:vec){
        std::cout<<val<<" ";
    }
    std::cout<<std::endl;
}

void printm(const Matrix &mat){
    for(int i=0;i<mat.size();i++){
        printvln(mat[i]);
    }
}

int main(){
    std::default_random_engine dre;
    dre.seed(time(NULL));
    int n;
    printf("输入矩阵阶数:\n");
    scanf("%d",&n);

    Matrix A(n,n);
    printf("\n系数矩阵为:\n");
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            A[i][j]=dre()%9+1;
            std::cout<<A[i][j]<<(j==n-1?"\n":" ");
        }
    }
    Matrix b(n,1);
    printf("\n常数项为:\n[");
    for(int i=0;i<n;i++){
        b[i][0]=dre()%9+1;
        std::cout<<b[i][0]<<(i==n-1?"]T\n":", ");
    }

    MaxColPivoting mcp(A,b);
    auto X1=mcp.solution();
    printf("\n列主元消去法的解为:\n");
    printvln(X1);
    printf("\n增广矩阵为:\n");
    for(int i=0;i<n;i++){
        printv(mcp.A[i]);
        std::cout<<"| "<<mcp.b[i][0]<<std::endl;
    }

    LUDecomp luf(A,b);
    auto X2=luf.solution();
    printf("\nLU分解法的解为:\n");
    printvln(X2);
    printf("\nLU分解的L矩阵为:\n");
    printm(luf.L);
    printf("\nLU分解的U矩阵为:\n");
    printm(luf.U);

    printf("\n以下为作业要求的方程组求解:\n");
    Matrix A2={{
        {31,-13,0,0,0,-10,0,0,0},
        {-13,35,-9,0,-11,0,0,0,0},
        {0,-9,31,-10,0,0,0,0,0},
        {0,0,-10,79,-30,0,0,0,-9},
        {0,0,0,-30,57,-7,0,-5,0},
        {0,0,0,0,-7,47,-30,0,0},
        {0,0,0,0,0,-30,41,0,0},
        {0,0,0,0,-5,0,0,27,-2},
        {0,0,0,-9,0,0,0,-2,29}
    }};
    Matrix b2={{
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

    MaxColPivoting mcp2(A2,b2);
    auto X3=mcp2.solution();
    printf("\n给定列主元消去法的解为:\n");
    printv(X3);
    printf("\n给定增广矩阵为:\n");
    for(int i=0;i<9;i++){
        printv(mcp2.A[i]);
        std::cout<<"| "<<mcp2.b[i][0]<<std::endl;
    }

    Matrix A3={{
        {30, 33, -43, -11, -38, -29, 37, 28, 23},
        {-480, -523, 644, 128, 621, 480, -618, -489, -329},
        {60, 266, -1862, -1991, 464, 546, -968, -1567, 1652},
        {540, 624, -782, 290, -893, 123, 567, 5, -122},
        {-450, -675, 2245, 2326, -1512, 1230, -822, 129, -189},
        {-300, -120, -1114, -1295, 1946, 302, -376, -1540, -609},
        {1080, 998, 508, 2460, -1628, -1358, 2896, 2828, -2002},
        {-1080, -1408, 3340, 2267, 21, -1202, 866, -2690, -1351},
        {-300, -435, 1594, 1685, 340, 2279, -27, 2917, -2336}
    }};
    Matrix b3={{
        {188},
        {-3145},
        {-4994},
        {680},
        {7845},
        {1876},
        {9712},
        {-11599},
        {10127}
    }};

    LUDecomp luf2(A3,b3);
    auto X4=luf2.solution();
    printf("\n给定LU分解法的解为:\n");
    printv(X4);
    printf("\n给定LU分解的L矩阵为:\n");
    printm(luf2.L);
    printf("\n给定LU分解的U矩阵为:\n");
    printm(luf2.U);
}