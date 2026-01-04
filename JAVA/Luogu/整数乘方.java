package Java.Luogu;

import java.math.*;
import java.util.*;

public class 整数乘方 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        BigInteger a=sc.nextBigInteger();
        int n=sc.nextInt();
        var pow=a.pow(n);
        int A=0,B=0;
        for(char c:pow.toString().toCharArray()){
            if(((c-'0')&1)==0){
                ++B;
            }else{
                ++A;
            }
        }
        System.out.println(A-B);
        sc.close();
    }
}
