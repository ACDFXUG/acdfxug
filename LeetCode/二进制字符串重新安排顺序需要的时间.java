package LeetCode;

public class 二进制字符串重新安排顺序需要的时间 {
    static int secondsToRemoveOccurrences(String s) {
        int ans=0;
        for(;s.contains("01");++ans){
            s=s.replaceAll("01","10");
        }
        return ans;
    }
    public static void main(String[] args) {
        String s="11100";
        System.out.println(secondsToRemoveOccurrences(s)); 
    }
}
