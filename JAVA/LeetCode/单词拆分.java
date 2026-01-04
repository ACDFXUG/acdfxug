package Java.LeetCode;

import java.util.List;

public class 单词拆分 {
    static boolean wordBreak(String s, List<String> wordDict) {
        final int n=s.length();
        boolean[] dp=new boolean[n+1];
        dp[0]=true;
        for(int i=1;i<=n;i++){
            for(int j=i-1;j>=0;j--){
                if(dp[j]&&wordDict.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        String s="catsandog";
        var wordDict=List.of("cats","dog","sand","and","cat");
        System.out.println(wordBreak(s, wordDict));
    }
}
