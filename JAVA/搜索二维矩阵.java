package JAVA;

public class 搜索二维矩阵 {
    static boolean searchMatrix(int[][] matrix, int target) {
        int row=matrix.length,col=matrix[0].length;
        if(target>matrix[row-1][col-1]||target<matrix[0][0]){
            return false;
        }
        int trow=0;
        for(int i=0;i<row;i++){
            if(matrix[i][0]<=target&&matrix[i][col-1]>=target){
                trow=i;
                break;
            }
        }
        for(int i=0;i<col;i++){
            if(matrix[trow][i]==target){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        
    }
}
