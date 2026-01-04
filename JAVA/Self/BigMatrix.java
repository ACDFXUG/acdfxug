package Java.Self;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class BigMatrix {
    static final BigDecimal one=BigDecimal.ONE,
                            zero=BigDecimal.ZERO; 
    static final Scanner sc=new Scanner(System.in);
    static BigDecimal NEG(int n){
        return (n&1)==1?zero.subtract(one):one;
    }
    static BigDecimal[][] PLUS(BigDecimal[][] a,BigDecimal[][] b){
        int m=a.length,n=a[0].length;
        BigDecimal[][] add=new BigDecimal[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                add[i][j]=a[i][j].add(b[i][j]);
            }
        }
        return add;
    }
    static BigDecimal[][] MINUS(BigDecimal[][] a,BigDecimal[][] b){
        int m=a.length,n=a[0].length;
        BigDecimal[][] sub=new BigDecimal[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                sub[i][j]=a[i][j].subtract(b[i][j]);
            }
        }
        return sub;
    }
    static BigDecimal[][] MULTIPLY(BigDecimal[][] a,BigDecimal[][] b){
        int m=a.length,n=a[0].length,o=b[0].length;
        BigDecimal[][] times=new BigDecimal[m][n];
        for(int i=0;i<m;i++){
            Arrays.fill(times[i],zero);
            for(int k=0;k<o;k++){
                for(int j=0;j<n;j++){
                    times[i][k]=times[i][k].add(a[i][j].multiply(b[j][k]));
                }
            }
        }
        return times;
    }
    static BigDecimal[][] SCALAR_MULT(BigDecimal[][] a,BigDecimal k){
        int m=a.length,n=a[0].length;
        BigDecimal[][] ans=new BigDecimal[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                ans[i][j]=a[i][j].multiply(k);
            }
        }
        return ans;
    }
    static BigDecimal[][] MATRIX_EXPON(BigDecimal[][] a,int n){
        int l=a.length;
        BigDecimal[][] power=new BigDecimal[l][l];
        if(n==0){
            for(int i=0;i<l;i++){
                for(int j=0;j<l;j++){
                    power[i][j]=(i==j)?one:zero;
                }
            }
        }else if(n>0){
            for(power=a.clone();n>=2;n--){
                power=MULTIPLY(power, a);
            }
        }
        return power;
    }
    static BigDecimal[][] TRANSPOSE(BigDecimal[][] a){
        int m=a[0].length,n=a.length;
        BigDecimal[][] trans=new BigDecimal[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                trans[i][j]=a[j][i];
            }
        }
        return trans;
    }
    static BigDecimal DETERMINE(BigDecimal[][] a){
        int n=a.length;
        if(n==1)return a[0][0];
        BigDecimal det=zero,minor[][]=new BigDecimal[n-1][n-1];
        for(int j=0;j<n;j++){
            for(int i=1,col=0;i<n;i++){
                for(int k=0;k<n;k++){
                    if(k!=j)minor[i-1][col++]=a[i][k];
                }
            }
            det=det.add(NEG(j).multiply(a[0][j]).multiply(DETERMINE(a)));
        }
        return det;
    }
    static BigDecimal[][] MINOR(BigDecimal[][] a,int i,int j){
        int m=a.length,n=a[0].length;
        BigDecimal[][] remain=new BigDecimal[m-1][n-1];
        for(int r=0;r<m;r++){
            if(r!=i){
                for(int c=0;c<n;c++){
                    if(c!=j)remain[r-(r<i?0:1)][c-(c<j?0:1)]=a[r][c];
                }
            }
        }
        return remain;
    }
    static BigDecimal[][] COFACTOR(BigDecimal[][] a,int i,int j){
        return SCALAR_MULT(MINOR(a, i, j), NEG(i+j));
    }
    static BigDecimal[][] ADJOINT(BigDecimal[][] a){
        int n=a.length;
        BigDecimal[][] adj=new BigDecimal[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                adj[i][j]=NEG(i+j).multiply(DETERMINE(MINOR(adj, i, j)));
            }
        }
        return adj;
    }
    static BigDecimal[][] INVERSE(BigDecimal[][] a){
        return SCALAR_MULT(ADJOINT(a),one.divide(DETERMINE(a)));
    }
    static BigDecimal TRACE(BigDecimal[][] a){
        int n=a.length;
        BigDecimal trace=zero;
        for(int i=0;i<n;trace=trace.add(a[i][i++]));
        return trace;
    }
    static void PRINT_MAT(BigDecimal[][] print){
        int m=print.length,n=print[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                System.out.printf(j==n-1?"%.2f\n":"%.2f ",print[i][j]);
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("输入矩阵的行数列数");
        int m=sc.nextInt(),n=sc.nextInt(),power=sc.nextInt();
        BigDecimal[][] a=new BigDecimal[m][n];
        System.out.println("输入矩阵");
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                a[i][j]=new BigDecimal(10*Math.random());
            }
        }
        System.out.println("所求矩阵为");
        PRINT_MAT(MATRIX_EXPON(a,power));
    }
}
