package JAVA.LeetCode;

public class 单词搜索 {
    static boolean dfs(char[][] board, String word, int x, int y, int index, boolean[][] visited) {
        // 边界检查
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }
        
        // 已访问或字符不匹配
        if (visited[x][y] || board[x][y] != word.charAt(index)) {
            return false;
        }
        
        // 找到完整单词
        if (index == word.length() - 1) {
            return true;
        }
        
        // 标记当前位置已访问
        visited[x][y] = true;
        
        // 四个方向搜索
        int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        for (int[] dir : dirs) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (dfs(board, word, newX, newY, index + 1, visited)) {
                return true;
            }
        }
        
        // 回溯：取消标记当前位置
        visited[x][y] = false;
        return false;
    }
    static boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        
        // 遍历每个可能的起点
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean[][] visited = new boolean[rows][cols];
                    if (dfs(board, word, i, j, 0, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        char[][] board={
            {'C','A','A'},
            {'A','A','A'},
            {'B','C','D'}
        };
        System.out.println(exist(board,"AAB"));
    }
}
