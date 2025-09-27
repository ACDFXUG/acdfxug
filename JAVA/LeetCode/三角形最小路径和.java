package JAVA.LeetCode;

import java.util.*;

public class 三角形最小路径和 {
    static int minimumTotal(List<List<Integer>> triangle) {
        int[][] dp=new int[triangle.size()+1][triangle.size()+1];
        for(int i=0;i<triangle.size()+1;i++){
            for(int j=0;j<triangle.size()+1;j++){
                dp[i][j]=Integer.MAX_VALUE;
            }
        }
        dp[1][1]=triangle.get(0).get(0);
        for(int i=1;i<triangle.size();i++){
            for(int j=1;j<=i+1;j++){
                dp[i+1][j]=Math.min(dp[i][j],dp[i][j-1])+triangle.get(i).get(j-1);
            }
        }
        int min=Integer.MAX_VALUE;
        for(int i=1;i<=triangle.size();i++){
            min=Math.min(min,dp[triangle.size()][i]);
        }
        return min;
    }
    public static void main(String[] args) {
        List<List<Integer>> triangle=List.of(
            List.of(2),
            List.of(3,4),
            List.of(6,5,7),
            List.of(4,1,8,3)
        );
        System.out.println(minimumTotal(triangle));
    }
}
