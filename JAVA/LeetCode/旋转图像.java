package JAVA.LeetCode;

import java.util.Arrays;

public class 旋转图像 {
    static void rotate(int[][] matrix) {
        final int n=matrix.length,half=n>>1;
        for(int i=0;i<half+(n&1);i++){
            for(int j=0;j<half;j++){
                int t=matrix[n-1-j][i];
                matrix[n-1-j][i]=matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j]=matrix[j][n-1-i];
                matrix[j][n-1-i]=matrix[i][j];
                matrix[i][j]=t;
            }
        }
    }
    public static void main(String[] args) {
        int[][] mat={
            {1,2,3},
            {4,5,6},
            {7,8,9}
        };
        rotate(mat);
        for(var i:mat){
            System.out.println(Arrays.toString(i));
        }
    }
}
