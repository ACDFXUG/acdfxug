package JAVA.Luogu;

import java.util.Scanner;

public class 台阶问题 {
    /*有N级台阶，你一开始在底部，每次可以向上迈1∼K级台阶，
    问到达第N级台阶有多少种不同方式。
    输出一个正整数ans( mod 100003)*/
    static int stage(int N,int K){
        int[] dp=new int[N+1];
        dp[0]=1;
        for(int i=1;i<=N;i++){
            for(int j=1;j<=K;j++){
                if(i-j>=0){
                    dp[i]+=dp[i-j];
                    dp[i]%=100003;
                }
            }
        }
        return dp[N];
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        int K=sc.nextInt();
        System.out.println(stage(N,K));
        sc.close();
    }
}
