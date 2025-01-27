package LeetCode;

public class 两数相除 {
    static int divide(int dividend, int divisor) {
        long x = dividend, y = divisor;
        long ans=x/y;
        if(ans>Integer.MAX_VALUE||ans<Integer.MIN_VALUE)
            return Integer.MAX_VALUE;
        else
            return (int)ans;
    }
    public static void main(String[] args) {
        System.out.println(divide(-(1<<31),-1));
    }
}
