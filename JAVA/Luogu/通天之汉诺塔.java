package JAVA.Luogu;

import java.util.*;
import java.math.*;
import static java.math.BigInteger.*;

public class 通天之汉诺塔 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        BigInteger ans=ONE.shiftLeft(N).subtract(ONE);
        System.out.println(ans);
        sc.close();
    }
}
