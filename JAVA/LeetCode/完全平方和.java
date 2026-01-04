package Java.LeetCode;

public class 完全平方和 {
    //给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
    static int numSquares(int n){
        int[] dp=new int[n+1];
        for(int i=1;i<=n;i++){
            dp[i]=Integer.MAX_VALUE;
            for(int j=1;j*j<=i;j++){
                dp[i]=Math.min(dp[i],dp[i-j*j]+1);
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        System.out.println(numSquares(12));
    }
}
