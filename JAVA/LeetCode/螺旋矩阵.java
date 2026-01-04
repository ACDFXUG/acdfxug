package Java.LeetCode;

import java.util.*;

public class 螺旋矩阵 {
    static List<Integer> spiralOrder(int[][] matrix) {
        int[] len={matrix.length*matrix[0].length};
        List<Integer> list=new ArrayList<Integer>(len[0]){{
            int i=0,j=0;
            while(len[0]>0){
                while(j<matrix[0].length&&matrix[i][j]!=-200){
                    add(matrix[i][j]);
                    matrix[i][j++]=-200;
                    len[0]--;
                }
                j--;
                i++;
                while(i<matrix.length&&matrix[i][j]!=-200){
                    add(matrix[i][j]);
                    matrix[i++][j]=-200;
                    len[0]--;
                }
                i--;
                j--;
                while(j>=0&&matrix[i][j]!=-200){
                    add(matrix[i][j]);
                    matrix[i][j--]=-200;
                    len[0]--;
                }
                j++;
                i--;
                while(i>=0&&matrix[i][j]!=-200){
                    add(matrix[i][j]);
                    matrix[i--][j]=-200;
                    len[0]--;
                }
                i++;
                j++;
            }
        }};
        return list;
    }
    public static void main(String[] args) {
        int[][] mat={
            {1,2,3},
            {4,5,6},
            {7,8,9}
        };
        System.out.println(spiralOrder(mat));
    }
}
