package LeetCode;

public class 字符串中的单词数 {
    static int countSegments(String s) {
        s=s.trim();
        if(s.length() == 0) return 0;
        return s.split("\\s+").length;
    }
    public static void main(String[] args) {
        
    }
}
