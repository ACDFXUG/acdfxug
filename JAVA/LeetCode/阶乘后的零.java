package Java.LeetCode;

public class 阶乘后的零 {
    
    static int trailingZeroes(int n) {
        int count = 0;
        int divisor = 5;
        while (divisor <= n) {
            count += n / divisor;
            divisor *= 5;
        }
        return count;
    }
    public static void main(String[] args) {
        int n=5;
        System.out.println(trailingZeroes(n));
    }
}
