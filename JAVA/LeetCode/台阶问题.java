package JAVA.LeetCode;

import java.util.Scanner;

public class 台阶问题 {
    static int climbStairs(int n){
        int[] stair=new int[46];
        stair[0]=stair[1]=1;
        for(int i=2;i<=n;i++){
            stair[i]=stair[i-1]+stair[i-2];
        }
        return stair[n];
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(climbStairs(n));
        sc.close();
    }
}
