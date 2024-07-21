package JAVA;

import java.util.Scanner;

public class shlw {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),k=sc.nextInt();
        int[] a=new int[k+1],h=new int[n+1];
        for(int i=1;i<=k;i++){
            a[i]=sc.nextInt();
        }
        for(int i=1;i<=k;i++){
            h[i]=sc.nextInt();
        }
        for(int s=k+1;s<=n;s++){
            for(int i=1;i<=k;i++){
                h[s]+=(a[i]*h[s-i])%1000000007;
            }
        }
        System.out.println(h[n]);
        sc.close();
    }
}
