package Luogu;

import java.math.*;
import java.util.*;

public class R格式 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        BigDecimal d=sc.nextBigDecimal();
        BigDecimal ans=d.multiply(
            new BigDecimal(BigInteger.ONE.shiftLeft(n))
        );
        System.out.println(ans.setScale(0,RoundingMode.HALF_UP));
        sc.close();
    }
}
