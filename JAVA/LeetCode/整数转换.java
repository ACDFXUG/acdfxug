package Java.LeetCode;

import java.util.Scanner;

public class 整数转换 {
    public static int convertInteger(int A,int B){
        int C=A^B,sum=0;
        for(;C>0;C>>=1){
            sum+=(C&1);
        }
        return sum;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int A=sc.nextInt(),B=sc.nextInt();
        System.out.println(convertInteger(A, B));
        sc.close();
    }
}
