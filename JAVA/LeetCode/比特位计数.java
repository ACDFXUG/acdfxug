package JAVA.LeetCode;

import java.util.Scanner;

public class 比特位计数 {
    static int[] countBit(int n){
        int[] bit=new int[n+1];
        for(int i=0;i<=n;i++){
            for(int s=i;s>0;s>>=1){
                bit[i]+=s&1;
            }
        }
        return bit;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for(int x:countBit(n)){
            System.out.printf("%d ",x);
        }
        sc.close();
    }
}
