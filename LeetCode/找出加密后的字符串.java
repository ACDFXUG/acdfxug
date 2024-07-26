package LeetCode;

public class 找出加密后的字符串 {
    static String getEncryptedString(String s, int k) {
        StringBuilder sb=new StringBuilder();
        for(int i=0,l=s.length();i<l;i++){
            sb.append(s.charAt((i+k)%l));
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String s="dart";
        int k=3;
        System.out.println(getEncryptedString(s, k));
    }
}
