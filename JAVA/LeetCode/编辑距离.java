package JAVA.LeetCode;

public class 编辑距离 {
    static int min(int... x){
        int res=x[0];
        for(int i:x){
            res=Math.min(res,i);
        }
        return res;
    }
    static int minDistance(String word1, String word2) {
        final int m=word1.length(),n=word2.length();
        int[][] dp=new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            dp[i][0]=i;
        }
        for(int j=0;j<=n;j++){
            dp[0][j]=j;
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    dp[i][j]=min(dp[i-1][j-1],dp[i][j-1],dp[i-1][j])+1;
                }
            }
        }
        return dp[m][n];
    }
    public static void main(String[] args) {
        System.out.println(minDistance("horse","ros"));
    }
}
