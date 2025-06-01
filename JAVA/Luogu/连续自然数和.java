package JAVA.Luogu;

import java.util.Scanner;

public class 连续自然数和 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        //对一个给定的正整数 M，求出所有的连续的正整数段（每一段至少有两个数），这些连续的自然数段中的全部数之和为 M
        //例如10000有答案1998~2002
        int M=sc.nextInt();
        for (int n = (int) Math.sqrt(2 * M); n >= 2; n--) {
            // 根据公式：2M = n*(2a + n - 1)
            if ((2 * M) % n != 0) continue;
            int temp = (2 * M) / n - n + 1;
            if (temp % 2 != 0 || temp <= 0) continue;
            int a = temp / 2;
            System.out.println(a + " " + (a + n - 1));
        }
        sc.close();
    }
}
