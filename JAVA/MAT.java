package JAVA;

import java.util.Scanner;

public class MAT {
    static int NEG(int n){  //(-1)^n
        return 1-((n&1)<<1);
    }
    static double[][] PLUS(double[][] a,double[][] b){  //加
        int m=a.length,n=a[0].length;
        double[][] add=new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                add[i][j]=a[i][j]+b[i][j];
            }
        }
        return add;
    }
    static double[][] MINUS(double[][] a,double[][] b){  //left减right
        int m=a.length,n=a[0].length;
        double[][] sub=new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sub[i][j]=a[i][j]-b[i][j];
            }
        }
        return sub;
    }
    static double[][] MULTIPLY(double[][] a,double[][] b){  //left*right
        int m=a.length,n=a[0].length,o=b[0].length;
        double[][] times=new double[m][o];
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < o; k++) {
                for (int j = 0; j < n; j++) {
                    times[i][k]+=a[i][j]*b[j][k];
                }
            }
        }
        return times;
    }
    static double[][] SCALAR_MULT(double[][] a,double k){  //数乘k*a
        int m=a.length,n=a[0].length;
        double[][] num=new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++){
                num[i][j]=a[i][j]*k;
            }
        }
        return num;
    }
    static double[][] POWER(double[][] a,int n){  //a^n
        int l=a.length;
        double[][] power=new double[l][l];
        if(n==0){
            for (int i = 0; i < l; power[i][i]=1,i++);
        }else if(n>0){
            for(power=a.clone();n>=2;n--){
                power=MULTIPLY(power, a);
            }
        }
        return power;
    }
    static double[][] TRANSPOSE(double[][] a){  //转置
        int m=a[0].length,n=a.length;
        double[][] trans=new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                trans[i][j]=a[j][i];
            }
        }
        return trans;
    }
    static double DETERMINE(double[][] a){  //行列式
        int n=a.length;
        if(n==1)return a[0][0];
        double det=0;
        double[][] minor=new double[n-1][n-1];
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                int col=0;
                for (int k = 0; k < n; k++) {
                    if(k!=j){
                        minor[i-1][col++]=a[i][k];
                    }
                }
            }
            det+=NEG(j)*a[0][j]*DETERMINE(minor);
        }
        return det;
    }
    static double[][] MINOR(double[][] a,int i,int j){  //余子式
        int m=a.length,n=a[0].length;
        double[][] remain=new double[m-1][n-1];
        for (int r=0;r<m;r++) {
            if(r!=i){
                for(int c=0;c<n;c++){
                    if(c!=j){remain[r-(r<i?0:1)][c-(c<j?0:1)]=a[r][c];}
                }
            }
		}
        return remain;
    }
    static double[][] COFACTOR(double[][] a,int i,int j){  //代数余子式
        return SCALAR_MULT(MINOR(a, i, j), NEG(i+j));
    }
    static double[][] ADJOINT(double[][] a){  //伴随矩阵
        int n=a.length;
        double[][] adj=new double[n][n];
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                adj[j][i]=NEG(i+j)*DETERMINE(MINOR(a, i, j));
            }
        }
        return adj;
    }
    static double[][] INVERSE(double[][] a){  //逆矩阵
        return SCALAR_MULT(ADJOINT(a), 1/DETERMINE(a));
    } 
    static double TRACE(double[][] a){  //迹
        int n=a.length;
        double trace=0;
        for (int i=0;i<n;trace+=a[i][i++]);
        return trace;
    }
    static void PRINT_MAT(double[][] print){  //打印矩阵
        int m=print.length,n=print[0].length;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                System.out.printf(j==n-1?"%.2f\n":"%.2f ",print[i][j]);
            }
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt(),n=sc.nextInt();
        double[][] a=new double[m][n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                a[i][j]=sc.nextDouble();
            }
        }
        System.out.println(DETERMINE(a));
        sc.close();
    }
}
