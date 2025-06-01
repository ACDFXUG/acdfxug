package JAVA.Luogu;

import java.util.Scanner;

public class 月落乌啼算钱 {
    static double FEBO(int n){
        if(n==0){
            return 0;
        }
        double[] febo=new double[n];
        febo[0]=febo[1]=1;
        for(int i=2;i<n;i++){
            febo[i]=febo[i-1]+febo[i-2];
        }
        return febo[n-1];
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.printf("%.2f",FEBO(n));
        sc.close();
    }
}
