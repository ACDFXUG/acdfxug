package Java.Luogu;

import java.util.Scanner;

public class 一的个数 {
    static int countDigitOne(int n) {
        int count = 0;
        int length = 1; // 当前段的长度，初始为1（个位）
        
        while (length <= n) {
            // 计算当前段的起始和结束值
            int start = (n / (length * 10)) * length * 10; // 当前段的起始值
            int end = Math.min(start + length, n + 1); // 当前段的结束值（含）

            // 计算当前段内1的总数
            count += (end - start) * length / 10; // 每段的基础1数量
            if (start / (length * 10) + 1 <= n % (length * 10) / length) {
                count += length; // 考虑进位增加的1
            }

            // 如果当前段长度不足以覆盖整个n，增加长度继续处理下一个段
            length *= 10;
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        System.out.println(countDigitOne(N));
        sc.close();
    }
}
