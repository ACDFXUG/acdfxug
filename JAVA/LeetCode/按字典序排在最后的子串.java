package JAVA.LeetCode;

public class 按字典序排在最后的子串 {
    static String lastSubstring(String s) {
        int n = s.length();
        int i = 0, j = 1, k = 0;
        
        while (j + k < n) {
            if (s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            } else if (s.charAt(i + k) < s.charAt(j + k)) {
                i = Math.max(i + k + 1, j);
                j = i + 1;
            k = 0;
            } else {
                j += k + 1;
                k = 0;
            }
        }
        
        return s.substring(i);
    }
    public static void main(String[] args) {
        System.out.println(lastSubstring("leetcode"));
    }
}
