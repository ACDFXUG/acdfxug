package JAVA.LeetCode;

public class 密钥格式化 {
    static String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != '-') {
                sb.append(s.charAt(i));
                count++;
                if (count % k == 0) {
                    sb.append('-');
                }
            }
            if (count == s.length()) {
                break;
            }
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '-') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.reverse().toString().toUpperCase();
    }
}
