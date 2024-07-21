package LeetCode;

import java.util.stream.IntStream;

public class 单调递增的数字 {
    static boolean isIncreasing(int k){
        while(k>0){
            if((k%10)>=((k/10)%10)){
                k/=10;
            }else{
                return false;
            }
        }
        return true;
    }
    static int monotoneIncreasingDigits(int n) {
        return IntStream.rangeClosed(0,n)
        .filter(i->isIncreasing(i))
        .max()
        .getAsInt();
    }
    public static void main(String[] args) {
        int n=1000000000;
        System.out.println(monotoneIncreasingDigits(n));
    }
}
