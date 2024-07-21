package LeetCode;

import java.util.Scanner;

public class 泰伯那契 {
    static int tribonacci(int n){
        int[] tri=new int[n+1];
        tri[0]=0;tri[1]=tri[2]=1;
        for(int i=3;i<=n;i++){
            tri[i]=tri[i-1]+tri[i-2]+tri[i-3];
        }
        return tri[n];
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        sc.close();
        System.out.println(tribonacci(n));
    }
}
