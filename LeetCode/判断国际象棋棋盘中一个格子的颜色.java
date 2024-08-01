package LeetCode;

public class 判断国际象棋棋盘中一个格子的颜色 {
    static boolean squareIsWhite(String coordinates) {
        int row=coordinates.charAt(1)-'0';
        int col=coordinates.charAt(0)-'a';
        return (row&1)==(col&1);
    }
    public static void main(String[] args) {
        
    }
}
