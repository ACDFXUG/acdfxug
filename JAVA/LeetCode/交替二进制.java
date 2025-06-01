package JAVA.LeetCode;

import java.util.Scanner;

public class 交替二进制 {
    public static boolean hasAlternatingBits(int n) {
        int[] a=new int[31];a[0]=1;
        for(int i=1;i<31;i++){
            a[i]=(i&1)==1?a[i-1]<<1:(a[i-1]<<1)+1;
        }
        for(int i=0;i<31;i++){
            if(n==a[i]){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(hasAlternatingBits(n));
        sc.close();
    }
}
