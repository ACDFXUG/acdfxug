package Luogu;

import java.util.*;
import java.math.*;

public class Hillwer编码 {
    static String toHillwer(String str,int R){
        StringBuilder ans=new StringBuilder();
        for(int i=0,l=str.length();i<l;i++){
            int index=str.charAt(i)-'A'+R;
            ans.append((char)('A'+index%26));
        }
        return ans.toString();
    }
    static BigInteger toHillwer(String str){
        BigInteger ans=BigInteger.ONE;
        for(int i=0,l=str.length();i<l;i++){
            ans=ans.multiply(BigInteger.valueOf(str.charAt(i)));
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int R=sc.nextInt();
        while(n-->0){
            String str=sc.next();
            String ans=toHillwer(str,R);
            System.out.println(ans);
            System.out.println(toHillwer(ans));
        }
        sc.close();
    }
}
