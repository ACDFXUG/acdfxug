package Java.Luogu;

import java.util.Scanner;

public class 向量内积 {
    static int DOT(int[] A,int[] B){
        int N=A.length,dot=0;
        for(int i=0;i<N;i++){
            dot+=A[i]*B[i];
        }
        return dot;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),d=sc.nextInt(),k=sc.nextInt(),t=0;
        int[][] vector=new int[n][d];
        for(int i=0;i<n;i++){
            for(int j=0;j<d;j++){
                vector[i][j]=sc.nextInt();
            }
        }
        OUT:for(int p=0;p<n;p++){
                for(int q=p+1;q<n;q++){
                    if(DOT(vector[p],vector[q])%k==0&&DOT(vector[p],vector[q])!=0){
                        System.out.printf("%d %d\n",p+1,q+1);
                        t++;
                        break OUT;
                    }
                }
            }
        if(t==0){
            System.out.printf("%d %d\n",-1,-1);
        }
        sc.close();
    }
}
