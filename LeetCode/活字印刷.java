package LeetCode;

import java.util.Arrays;

public class 活字印刷 {
    static int count = 0;
    static void backtrack(char[] chars, boolean[] used) {
        for (int i = 0; i < chars.length; i++) {
            // 跳过已使用的字符
            if (used[i]) continue;
            // 跳过重复字符
            if (i > 0 && chars[i] == chars[i - 1] && !used[i - 1]) continue;

            // 标记当前字符为已使用
            used[i] = true;
            count++; // 计数新的排列

            // 递归处理下一个字符
            backtrack(chars, used);

            // 回溯：撤销当前字符的使用标记
            used[i] = false;
        }
    }
    static int numTilePossibilities(String tiles) {
        char[] chars = tiles.toCharArray();
        Arrays.sort(chars); // 排序以便处理重复字符
        boolean[] used = new boolean[chars.length];
        backtrack(chars, used);
        return count;
    }
    public static void main(String[] args) {
        System.out.println(numTilePossibilities("AAB"));
    }
}
