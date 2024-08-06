package Luogu;

import java.util.*;

public class AdjacentSwaps {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), Q = sc.nextInt();
        
        // 初始化数组
        int[] location = new int[N + 1]; // 存储元素的位置
        int[] indexMap = new int[N + 1]; // 存储每个元素的索引
        
        // 初始化数组和索引映射
        for (int i = 1; i <= N; i++) {
            location[i] = i;
            indexMap[i] = i; // 记录每个元素的初始位置
        }
        
        while (Q-->0) {
            int ai = sc.nextInt();
            
            // 获取元素ai的当前索引
            int j = indexMap[ai];
            
            // 根据索引进行位置交换
            if (j == N) {
                // 与前一个元素交换
                int temp = location[j - 1];
                location[j - 1] = ai;
                location[j] = temp;
                
                // 更新索引映射
                indexMap[ai] = j - 1;
                indexMap[temp] = j;
            } else {
                // 与后一个元素交换
                int temp = location[j + 1];
                location[j + 1] = ai;
                location[j] = temp;
                
                // 更新索引映射
                indexMap[ai] = j + 1;
                indexMap[temp] = j;
            }
        }
        
        // 输出结果
        for (int i = 1; i <= N; i++) {
            System.out.print(location[i] + " ");
        }
        sc.close();
    }
}
