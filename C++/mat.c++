#include <iostream>
#include <vector>
#define MatrixD std::vector<std::vector<double> >
#define VectorD std::vector<double>

int neg(int n){ //(-1)^n
    return 1-((n&1)<<1);
}

MatrixD plus(MatrixD a,MatrixD b){
    int m=a.size(),n=a[0].size();
    MatrixD add(m,VectorD(n));
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            add[i][j]=a[i][j]+b[i][j];
        }
    }
    return add;
}

MatrixD minus(MatrixD a,MatrixD b){
    int m=a.size(),n=a[0].size();
    MatrixD sub(m,VectorD(n));
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            sub[i][j]=a[i][j]-b[i][j];
        }
    }
    return sub;
}

MatrixD multiply(MatrixD a,MatrixD b){
    int m=a.size(),n=a[0].size(),o=b[0].size();
    MatrixD times(m,VectorD(n));
    for(int i=0;i<m;i++){
        for(int k=0;k<o;k++){
            for(int j=0;j<n;j++){
                times[i][k]+=a[i][j]*b[i][j];
            }
        }
    }
    return times;
}

MatrixD scalarMult(MatrixD a,double k){
    int m=a.size(),n=a[0].size();
    MatrixD num(m,VectorD(n));
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            num[i][j]=k*a[i][j];
        }
    }
    return num;
}

MatrixD matrixExpon(MatrixD a,int n){ //a^n,n>=0
    int l=a.size();
    MatrixD power(l,VectorD(l));
    if(n==0){
        for(int i=0;i<l;power[i][i++]=1);
    }else{
        for(power=a;n>=2;n--){
            power=multiply(power,a);
        }
    }
    return power;
}

MatrixD transpose(MatrixD a){
    int m=a[0].size(),n=a.size();
    MatrixD trans(m,VectorD(n));
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            trans[i][j]=a[j][i];
        }
    }
    return trans;
}

double determine(MatrixD a){
    int n=a.size();
    double det=0.0;
    if(n==0){
        return a[0][0];
    }
    MatrixD minor(n-1,VectorD(n-1));
    for (int j=0;j<n;j++) {
        for (int i=1,col=0;i<n;i++) {
            for (int k=0;k<n;k++) {
                if(k!=j){
                    minor[i-1][col++]=a[i][k];
                }
            }
        }
        det+=neg(j)*a[0][j]*determine(minor);
    }
    return det;
}

MatrixD minor(MatrixD a,int i,int j){
    int m=a.size(),n=a[0].size();
    MatrixD remain(m,VectorD(n));
    for (int r=0;r<m;r++) {
        if(r!=i){
            for(int c=0;c<n;c++){
                if(c!=j){
                    remain[r-(r<i?0:1)][c-(c<j?0:1)]=a[r][c];
                }
            }
        }
	}
    return remain;    
}

MatrixD cofactor(MatrixD a,int i,int j){
    return scalarMult(minor(a,i,j), neg(i+j));
}

MatrixD adjoint(MatrixD a){
    int n=a.size();
    MatrixD adj(n,VectorD(n));
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            adj[i][j]=neg(i+j)*determine(minor(a,i,j));
        }
    }
    return adj;
}

MatrixD inverse(MatrixD a){
    return scalarMult(adjoint(a),1/determine(a));
}

double trace(MatrixD a){
    int n=a.size();
    double tr=0.0;
    for(int i=0;i<n;tr+=a[i][i++]);
    return tr;
}

void printM(MatrixD print){
    int m=print.size(),n=print[0].size();
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            printf(j==n-1?"%.2f\n":"%.2f ",print[i][j]);
        }
    }
}

int main(){
    int m,n;
    std::cin>>m>>n;
    MatrixD a(m,VectorD(n));
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            scanf("%lf",&a[i][j]);
        }
    }
    printf("%.2f\n",trace(a));
}