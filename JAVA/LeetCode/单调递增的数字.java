package Java.LeetCode;

public class 单调递增的数字 {
    // static boolean isIncreasing(int k){
    //     while(k>0){
    //         if((k%10)>=((k/10)%10)){
    //             k/=10;
    //         }else{
    //             return false;
    //         }
    //     }
    //     return true;
    // }
    static int monotoneIncreasingDigits(int n) {
        char[] digits = String.valueOf(n).toCharArray();
        int mark = digits.length; // 标记需要修改的位置

        // 从高位到低位扫描
        for (int i = digits.length - 1; i > 0; i--) {
            if (digits[i] < digits[i - 1]) {
                mark = i; // 记录需要修改的位置
                digits[i - 1]--; // 当前位减 1
            }
        }

        // 将标记位置及之后的所有位设置为 '9'
        for (int i = mark; i < digits.length; i++) {
            digits[i] = '9';
        }

        return Integer.parseInt(new String(digits));
    }
    public static void main(String[] args) {
        int n=709219028;
        System.out.println(monotoneIncreasingDigits(n));
    }
}
