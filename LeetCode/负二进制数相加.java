package LeetCode;

import java.math.BigInteger;
import java.util.*;

public class 负二进制数相加 {
    static BigInteger toNegaBinary(int[] arr){
        BigInteger init=BigInteger.ZERO;
        final BigInteger negaTwo=BigInteger.valueOf(-2);
        for(int x:arr){
            init=init.multiply(negaTwo).add(BigInteger.valueOf(x));
        }
        return init;
    }
    static int[] addNegabinary(int[] arr1, int[] arr2) {
        BigInteger a=toNegaBinary(arr1);
        BigInteger b=toNegaBinary(arr2);
        BigInteger c=a.add(b);
        final BigInteger negaTwo=BigInteger.valueOf(-2);
        List<Integer> ans=new ArrayList<>();
        while(c.compareTo(BigInteger.ZERO)!=0){
            ans.add(c.mod(negaTwo).intValue());
            c=c.divide(negaTwo);
        }
        Collections.reverse(ans);
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
    public static void main(String[] args) {
        int[] a1={1,1,1,1,1};
        int[] a2={1,0,1};
        System.out.println(Arrays.toString(addNegabinary(a1, a2)));
    }
}
