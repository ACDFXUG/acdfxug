package Java.LeetCode;

public class 搜索二维矩阵II{
    static boolean searchMatrix(int[][] matrix, int target) {
        // for(var arr:matrix){
        //     if(java.util.Arrays.binarySearch(arr,target)>=0){
        //         return true;
        //     };
        // }
        // return false;
        int row = 0;
        int col = matrix[0].length - 1;
        
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        
        return false;
    }
    public static void main(String[] args) {
        
    }
}