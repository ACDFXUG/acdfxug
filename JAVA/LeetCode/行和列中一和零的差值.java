package Java.LeetCode;

public class 行和列中一和零的差值 {
    static int[][] onesMinusZeros(int[][] grid) {
        final int m=grid.length,n=grid[0].length;
        var diff=new int[m][n];
        var onesRow=new int[m];
        var onesCol=new int[n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                onesRow[i]+=grid[i][j];
                onesCol[j]+=grid[i][j];
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                diff[i][j]=((onesRow[i]+onesCol[j])<<1)-m-n;
            }
        }
        return diff;
    }
    public static void main(String[] args) {
        
    }
}
