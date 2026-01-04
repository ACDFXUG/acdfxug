package Java.LeetCode;

import java.util.*;

public class 竖直打印单词 {
    static List<String> printVertically(String s) {
        String[] strs = s.split(" ");
        int max = 0;
        for (String str : strs) {
            max = Math.max(max, str.length());
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            StringBuilder sb = new StringBuilder();
            for (String str : strs) {
                if (i < str.length()) {
                    sb.append(str.charAt(i));
                } else {
                    sb.append(" ");
                }
            }
            while (sb.charAt(sb.length() - 1) == ' ') {
                sb.deleteCharAt(sb.length() - 1);
            }
            res.add(sb.toString());
        }
        return res;
    }
    public static void main(String[] args) {
        String s="TO BE OR NOT TO BE";
        printVertically(s).forEach(str->System.out.println(str));
    }
}
