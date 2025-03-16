package LeetCode;

public class 二维区域和检索 {
    @SuppressWarnings("unused")
    private static class NumMatrix{
        final int[][] prefix;
        NumMatrix(int[][] matrix){
            this.prefix=new int[matrix.length][matrix[0].length];
            for(int i=0;i<matrix.length;i++){
                for(int j=0;j<matrix[0].length;j++){
                    prefix[i][j]=(i==0?0:prefix[i-1][j])+(j==0?0:prefix[i][j-1])-
                    (i==0||j==0?0:prefix[i-1][j-1])+matrix[i][j];
                }
            }
        }
        int sumRegion(int row1, int col1, int row2, int col2) {
            return prefix[row2][col2]-
            (row1==0?0:prefix[row1-1][col2])-(col1==0?0:prefix[row2][col1-1])+
            (row1==0||col1==0?0:prefix[row1-1][col1-1]);
        }
    }
    public static void main(String[] args) {
        
    }
}
