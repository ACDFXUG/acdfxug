package Luogu;

import java.util.Scanner;

public class 康托表 {
    static int index(int N){
        int n=(int)Math.sqrt(2*N);
        for(int i=n-1;i<=n+1;++i){
            if(N>=(i-1)*i/2&&N<=i*(i+1)/2){
                return i;
            }
        }
        return 0;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),INDEX=index(N),ord=N-(INDEX-1)*INDEX/2;
        System.out.println((INDEX&1)==1?(INDEX+1-ord)+"/"+ord:ord+"/"+(INDEX+1-ord));
        sc.close();
    }
}
