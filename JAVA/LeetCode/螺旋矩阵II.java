package JAVA.LeetCode;

public class 螺旋矩阵II {
    static int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int i = 0, j = 0, k = 1;
        while (k <= n * n) {
            while (j < n && res[i][j] == 0) {
                res[i][j++] = k++;
            }
            j--;
            i++;
            while (i < n && res[i][j] == 0) {
                res[i++][j] = k++;
            }
            i--;
            j--;
            while(j >= 0 && res[i][j] == 0){
                res[i][j--] = k++;
            }
            j++;
            i--;
            while(i >= 0 && res[i][j] == 0){
                res[i--][j] = k++;
            }
            i++;
            j++;
        }
        return res;
    }
    public static void main(String[] args) {
        
    }
}
