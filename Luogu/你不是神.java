package Luogu;

import java.util.Scanner;

public class 你不是神 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        long n=sc.nextLong(),m=sc.nextLong(),q=sc.nextLong(),sum=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                sum+=i*j%q;
            }
        }
        System.out.println(sum);
        sc.close();
    }
}
