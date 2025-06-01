package JAVA.LeetCode;

import java.util.Arrays;

public class 根据第K场考试的分数排序 {
    static int[][] sortTheStudents(int[][] score, int k) {
        Arrays.sort(score,(arr1,arr2)->arr2[k]-arr1[k]);
        return score;
    }
    public static void main(String[] args) {
        
    }
}
