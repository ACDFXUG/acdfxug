#include <iostream>
#include <vector>
#include <cmath>
#define array std::vector<double>
#define matrix std::vector<array > 

class Matrix{
    private:
    int rows,cols;
    matrix data;
    int negative(int n){
        return (n&1)?-1:1;
    }
    public:
    Matrix(int row,int col){
        this->rows=row;
        this->cols=col;
        this->data.resize(row,array(col,0));
    }
    Matrix(int order){
        this->rows=this->cols=order;
        this->data.resize(order,array(order,0));
    }
    Matrix(matrix x){
        this->rows=x.size();
        this->cols=x[0].size();
        this->data=x;
    }
    Matrix(int row,int col,double init){
        this->rows=row;
        this->cols=col;
        this->data.resize(row,array(col,init));
    }
    Matrix(int order,double init){
        this->rows=this->cols=order;
        this->data.resize(order,array(order,init));
    }
    Matrix(const Matrix &mat){
        this->data=mat.data;
        this->rows=mat.rows;
        this->cols=mat.cols;
    }
    Matrix(Matrix &&x){
        this->data=std::move(x.data);
        this->rows=x.rows;
        this->cols=x.cols;
    }
    void assign(matrix x){
        data=x;
    }
    matrix toArrays(){
        return data;
    }
    void nextValue(double x,int i,int j){
        data[i][j]=x;
    }
    void initialize(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                data[i][j]=0;
            }
        }
    }
    void initialize(double x){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                data[i][j]=x;
            }
        }
    }
    
    Matrix identity(int order){
        Matrix x(order);
        for(int i=0;i<order;i++){
            x.data[i][i]=1;
        }
        return x;
    }
    double getValue(int i,int j) const{
        return data[i][j];
    }
    double &operator()(const int &i,const int &j){
        return data[i][j];
    }
    array &operator[](const int &i){
        return data[i];
    }
    double &operator [](const int &i,const int &j){
        return data[i][j];
    } 
    bool operator ==(const Matrix &x) const{
        if(this==&x)return true;
        return data==x.data;
    }
    Matrix clone(){
        return Matrix(data);
    }
    Matrix copyOf(int row,int col){
        matrix x=data;
        x.resize(row,array(col,0));
        return Matrix(x);
    }
    static Matrix copyOf(Matrix a,int row,int col){
        Matrix ans(row,col);
        for(int i=0;i<std::min(row,a.rows);i++){
            for(int j=0;j<std::min(col,a.cols);j++){
                ans[i][j]=a[i][j];
            }
        }
        return ans;
    }
    void printM(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                std::cout<<data[i][j]<<" ";
            }
            printf("\n");
        }
    }
    static void printM(Matrix x){
        for(int i=0;i<x.rows;i++){
            for(int j=0;j<x.cols;j++){
                std::cout<<x.data[i][j]<<" ";
            }
            printf("\n");
        }
    }
    Matrix &operator =(const Matrix &x){
        data=x.data;
        rows=x.rows;
        cols=x.cols;
        return *this;
    }
    Matrix operator +(const Matrix &x) const{
        Matrix temp(rows,cols);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                temp.data[i][j]=data[i][j]+x.data[i][j];
            }
        }
        return temp;
    }
    // void operator +=(const Matrix &x){
    //     for(int i=0;i<rows;i++){
    //         for(int j=0;j<cols;j++){
    //             data[i][j]+=x.data[i][j];
    //         }
    //     }
    // }

    Matrix &operator +=(const Matrix &x){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                data[i][j]+=x.data[i][j];
            }
        }
        return *this;
    }
    Matrix operator -(const Matrix &x){
        Matrix temp(rows,cols);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                temp.data[i][j]=data[i][j]-x.data[i][j];
            }
        }
        return temp;
    }
    // void operator -=(const Matrix &x){
    //     for(int i=0;i<rows;i++){
    //         for(int j=0;j<cols;j++){
    //             data[i][j]-=x.data[i][j];
    //         }
    //     }
    // }

    Matrix &operator -=(const Matrix &x){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                data[i][j]-=x.data[i][j];
            }
        }
        return *this;
    }
    Matrix operator *(const Matrix &x){
        Matrix temp(rows,x.cols);
        for(int i=0;i<rows;i++){
            for(int k=0;k<x.cols;k++){
                for(int j=0;j<cols;j++){
                    temp.data[i][k]+=data[i][j]*x.data[j][k];
                }
            }
        }
        return temp;
    }
    // void operator *=(const Matrix &x){
    //     Matrix c(rows,cols);
    //     for(int i=0;i<rows;i++){
    //         for(int k=0;k<cols;k++){
    //             for(int j=0;j<cols;j++){
    //                 c.data[i][k]+=data[i][j]*x.data[j][k];
    //             }
    //         }
    //     }
    //     data=c.data;
    // }

    Matrix &operator *=(const Matrix &x){
        Matrix c(rows,cols);
        for(int i=0;i<rows;i++){
            for(int k=0;k<cols;k++){
                for(int j=0;j<cols;j++){
                    c.data[i][k]+=data[i][j]*x.data[j][k];
                }
            }
        }
        return *this=c;
    }
    Matrix hadamard(Matrix x){
        Matrix p=clone();
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                p.data[i][j]=data[i][j]*x.data[i][j];
            }
        }
        return p;
    }
    Matrix operator *(const double &k){
        Matrix c=clone();
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                c.data[i][j]=data[i][j]*k;
            }
        }
        return c;
    }
    // void operator *=(const double &k){
    //     for(int i=0;i<rows;i++){
    //         for(int j=0;j<cols;j++){
    //             data[i][j]*=k;
    //         }
    //     }
    // }

    Matrix &operator *=(const double &k){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                data[i][j]*=k;
            }
        }
        return *this;
    }
    Matrix operator ^(const int &n){
        Matrix c=clone();
        if(n==0){
            c=identity(rows);
        }else{
            for(int s=n;s>=2;s--){
                c*=clone();
            }
        }
        return c;
    }
    void operator ^=(const int &n){
        Matrix c=clone();
        if(n==0){
            this->data=identity(rows).data;
        }else{
            for(int s=n;s>1;s--){
                c*=clone();
            }
            this->data=c.data;
        }
    }
    Matrix transpose(){
        Matrix c(cols,rows);
        for (int i=0;i<cols;i++) {
            for (int j=0;j<rows;j++) {
                c.data[i][j]=data[j][i];
            }
        }
        return c;
    }
    private:
    double deter(matrix mat){
        // int p=mat.size();
        // if(p==1){
        //     return mat[0][0];
        // }
        // ld det=.0l;
        // if(p==2) {
        //     return mat[0][0]*mat[1][1]-mat[0][1]*mat[1][0];
        // }
        // // 使用迭代方法
        // std::vector<int> perm(p);
        // for(int i=0;i<p;i++){
        //     perm[i]=i;
        // }
        // do{
        //     ld sign=1,prod=1;
        //     for(int i=1;i<p;i++) {
        //         for(int j=0;j<i;j++) {
        //             if (perm[j]>perm[i]) {
        //                 sign*=-1;
        //             }
        //         }
        //     }
        //     for(int i=0;i<p;i++) {
        //         prod*=mat[i][perm[i]];
        //     }
        //     det+=sign*prod;
        // }while(std::next_permutation(perm.begin(),perm.end()));
        int n=mat.size();
        if(n==1)return mat[0][0];
        double det=0;
        matrix minor(n-1,array(n-1,0));
        for (int j=0;j<n;j++){
            for (int i = 1; i < n; i++) {
                int col=0;
                for(int k = 0; k < n; k++) {
                    if(k!=j){minor[i-1][col++]=mat[i][k];}
                }
            }
            det+=negative(j)*mat[0][j]*deter(minor);
        }
        return det;
    }
    public:
    double determine(){
        return deter(data);
    }
    static double determine(Matrix x){
        return x.determine();
    }
    Matrix minor(int i,int j){
        Matrix p(rows-1,cols-1);
        for(int r=0;r<rows;r++){
            if(r!=i){
                for(int c=0;c<cols;c++){
                    if(c!=j){
                        p.data[r-(r>i)][c-(c>j)]=data[r][c];
                    }
                }
            }
        }
        return p;
    }
    Matrix cofactor(int i,int j){
        return minor(i,j)*negative(i+j);
    }
    Matrix adjoint(){
        Matrix c=clone();
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                c.data[j][i]=minor(i,j).determine()*negative(i+j);
            }
        }
        return c;
    }
    Matrix inverse(){
        return adjoint()*(1/determine());
    }
    double trace(){
        double trace=0;
        for(int i=0;i<rows;i++){
            trace+=data[i][i];
        }
        return trace;
    }
    
    bool operator >(const Matrix &x){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(data[i][j]<=x.data[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    bool operator <(const Matrix &x){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(data[i][j]>=x.data[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    Matrix operator |(const Matrix &x){
        Matrix c(rows,cols);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                c.data[i][j]=(int)data[i][j]|(int)x.data[i][j];
            }
        }
        return c;
    }
    Matrix operator &(const Matrix &x){
        Matrix c(rows,cols);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                c.data[i][j]=(int)data[i][j]&(int)x.data[i][j];
            }
        }
        return c;
    }
    Matrix operator &&(const Matrix &x){
        Matrix c(rows);
        for(int i=0;i<rows;i++){
            for(int j=0;j<rows;j++){
                for(int k=0;k<rows;k++){
                    c.data[i][j]=(int)c.data[i][j]|((int)data[i][j]&(int)x.data[i][j]);
                }
            }
        }
        return c;
    }
    Matrix replaceRow(int i,array x){
        Matrix t(data);
        t.data[i]=x;
        return t;
    }
    Matrix replaceCol(int i,array x){
        Matrix t(data);
        for(int s=0;s<rows;t.data[s][i]=x[s++]);
        return t;
    }
    static array solution(Matrix coefficient,array x){
        array solution(coefficient.rows,0);
        double det=determine(coefficient);
        for(int i=0;i<coefficient.rows;i++){
            solution[i]=determine(coefficient.replaceCol(i,x))/det;
        }
        return solution;
    }
    private:
    void Reduce(matrix mat){
        int pivot=0;
        for(int r=0;r<rows;r++,pivot++){
            if(cols<=pivot)return;
            int i=r;
            for(;mat[i][pivot]==0;){
                i++;
                if(i==rows){
                    i=r;
                    pivot++;
                    if(cols==pivot)return;
                }
            }
            double temp=mat[r][pivot];
            mat[r][pivot]=mat[i][pivot];
            mat[i][pivot]=temp;
            if(mat[r][pivot]!=0){
                double scalar=mat[r][pivot];
                for(int j=0;j<cols;mat[r][j++]/=scalar);
            }
            for(int k=0;k<rows;k++){
                if(k!=r){
                    double scalar=mat[k][pivot];
                    for(int j=0;j<cols;mat[k][j]-=scalar*mat[r][j++]);
                }
            }
        }
    }
    static bool isAllZero(matrix p,int i){
        for(int j=0;j<p[0].size();j++){
            if(p[i][j]!=0){
                return false;
            }
        }
        return true;
    }
    public:
    Matrix toReducedEchelon(){
        matrix ech=clone().data;
        Reduce(ech);
        return Matrix(ech);
    }
    static Matrix toReducedEchelon(Matrix a){
        return a.toReducedEchelon();
    }
    int rank(){
        int rank=0;
        matrix t=toReducedEchelon().data;
        for(int i=0;i<t.size();i++){
            if(!isAllZero(t,i)){
                rank++;
            }
        }
        return rank;
        // Matrix p=clone();
        // int rank=0;
        // for(int i=0;i<cols;i++){
        //     int r=0,c=0;
        //     for(c=i;c<cols;c++){
        //         for(r=i;r<rows;r++){
        //             if(abs(p.data[r][c])!=0){
        //                 goto out;
        //             }
        //         }
        //     }
        // out:if(r<rows&&c<cols){
        //         for(int j=c;j<cols;j++){
        //             std::swap(p.data[i][j],p.data[r][j]);
        //         }
        //         for(int j=i+1;j<rows;j++){
        //             double a=p.data[j][c]/p.data[i][c];
        //             for(int k=c;k<cols;k++){
        //                 p.data[j][k]-=a*p.data[i][k];
        //             }
        //         }
        //         rank++;
        //     }else{
        //         break;
        //     }
        // }
        // return rank;
    }
    static int rank(Matrix x){
        return x.rank();
    }
    static array gaussSolution(Matrix coeff,array b){
        int m=coeff.rows,n=coeff.cols;
        Matrix expand(m,n+1);
        for(int i=0;i<m;i++){
            for(int j=0;j<=n;j++){
                if(j<n){
                    expand[i][j]=coeff[i][j];
                }else{
                    expand[i][j]=b[i];
                }
            }
        }
        if(n>m){
            throw;
        }else{
            int r1=rank(coeff),r2=rank(expand);
            if(r1==r2){
                if(r1<n){
                    throw;
                }else{
                    Matrix re=expand.toReducedEchelon();
                    Matrix sub=copyOf(re,n,n);
                    array bb(n);
                    for(int i=0;i<n;bb[i]=re[i++][n]);
                    return solution(sub,bb);
                }
            }else{
                throw;
            }
        }
    }
};

