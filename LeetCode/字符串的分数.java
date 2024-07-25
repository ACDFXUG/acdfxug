package LeetCode;

public class 字符串的分数 {
    static int scoreOfString(String s) {
        int ans=0;
        for(int i=0,l=s.length();i<l-1;i++){
            ans+=Math.abs(s.charAt(i)-s.charAt(i+1));
        }
        return ans;
    }
    public static void main(String[] args) {
        
    }
}
