package LeetCode;

public class 构造乘积矩阵 {
    static int[][] constructProductMatrix(int[][] grid) {
        final int n = grid.length;
        final int m = grid[0].length;
        final int MOD = 12345;

        // 结果矩阵
        int[][] ans = new int[n][m];

        // 计算前缀乘积
        long prefix = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans[i][j] = (int) prefix;
                prefix = (prefix * grid[i][j]) % MOD;
            }
        }

        // 计算后缀乘积并更新结果矩阵
        long suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                ans[i][j] = (int) ((ans[i][j] * suffix) % MOD);
                suffix = (suffix * grid[i][j]) % MOD;
            }
        }

        return ans;
    }
    public static void main(String[] args) {
        var grid=new int[][]{
            {10,20,30},
            {40,50,60}
        };
        System.out.println(
            java.util.Arrays.deepToString(constructProductMatrix(grid)));
    }
}
