package JAVA.Luogu;

import java.math.BigInteger;
import java.util.Scanner;

public class 广义斐波那契数列 {
    static BigInteger[][] MAT_POW(BigInteger[][] A,int n){
        if(n==1){
            return A;
        }else{
            BigInteger[][] AA=new BigInteger[2][2];
            for(int s=2;s<=n;s++){
                for(int i=0;i<2;i++){
                    for(int j=0;j<2;j++){
                        for(int k=0;k<2;k++){
                            AA[i][j]=AA[i][j].add(A[i][k].multiply(A[k][j]));
                        }
                    }
                }
            }
            return AA;
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        BigInteger[][] A={{new BigInteger("1"),new BigInteger("1")},
        {new BigInteger("1"),new BigInteger("0")}};
        BigInteger[][] B=MAT_POW(A, n-1);
        System.out.println(B[1][0].add(B[1][1]));
        sc.close();
    }
}
