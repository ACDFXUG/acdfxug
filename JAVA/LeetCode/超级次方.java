package JAVA.LeetCode;

import java.math.*;
import static java.math.BigInteger.*;

public class 超级次方 {
    static int superPow(int a, int[] b) {
        StringBuilder B=new StringBuilder();
        for(int i=0;i<b.length;i++){
            B.append(b[i]);
        }
        return valueOf(a).modPow(new BigInteger(B.toString()),valueOf(1337)).intValue();
    }
    public static void main(String[] args) {
        System.out.println(superPow(0x7fffffff,new int[]{2,0,0}));
    }
}
