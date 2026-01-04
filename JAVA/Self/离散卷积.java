package Java.Self;

import java.util.Scanner;

public class 离散卷积 {
    static double[] DIS_CONV(double[] A,double[] B){ //要求A.length>=B.length
        int m=A.length,n=B.length;  //m>=n
        double[] CON=new double[m+n-1];
        for(int i=0;i<CON.length;i++){
            if(i<n){
                for(int k=0;k<=i;k++){
                    CON[i]+=A[k]*B[i-k];
                }
            }else if(i>=n&&i<m){
                for(int k=i-n+1;k<=i;k++){
                    CON[i]+=A[k]*B[i-k];
                }
            }else if(i>=m){
                for(int k=i-m+1;k<n;k++){
                    CON[i]+=B[k]*A[i-k];
                }
            }
        }
        return CON;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt(),n=sc.nextInt();
        double[] A=new double[m],B=new double[n];
        for(int i=0;i<m;i++){
            A[i]=sc.nextDouble();
        }
        for(int j=0;j<n;j++){
            B[j]=sc.nextDouble();
        }
        for(int k=0;k<m+n-1;k++){
            System.out.printf("%.2f ",DIS_CONV(A, B)[k]);
        }
        sc.close();
    }
}
