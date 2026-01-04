package Java.LeetCode;

import java.util.Scanner;

public class 数位相加 {
    static int digitSum(int n){
        return n==0?0:(n-1)%9+1;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<1000;i++){
            System.out.println(digitSum(i));
        }
        sc.close();
    }
}
