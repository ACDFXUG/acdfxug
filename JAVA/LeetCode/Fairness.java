package JAVA.LeetCode;

import java.util.*;

@SuppressWarnings("unused")
public class Fairness {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int A=sc.nextInt(),B=sc.nextInt()
            ,C=sc.nextInt();
        long K=sc.nextLong();
        long ans=(K&1)==1?B-A:A-B;
        System.out.println(Math.abs(ans)>
        10000_0000_0000_0000_00L?"Unfair":ans);
        sc.close();
    }
}
