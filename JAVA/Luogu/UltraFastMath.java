package JAVA.Luogu;

import java.util.Scanner;

public class UltraFastMath {
    static String xor(String a,String b){
        int L=a.length();
        String ans=new String();
        for(int i=0;i<L;i++){
            ans+=String.valueOf((a.charAt(i)-'0')^(b.charAt(i)-'0'));
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String a=sc.next(),b=sc.next();
        System.out.println(xor(a, b));
        sc.close();
    }
}
