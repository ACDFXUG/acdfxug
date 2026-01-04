package Java.Luogu;

import java.math.BigInteger;
import java.util.Scanner;

public class 阶乘数码 {
    static BigInteger FACT(int n){
        BigInteger INIT=new BigInteger("1");
        if(n<=1){
            return INIT;
        }else {
            for(int i=2;i<=n;i++){
                INIT=INIT.multiply(new BigInteger(Integer.toString(i)));
            }
            return INIT;
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        for(int h=1;h<=t;h++){
            int n=sc.nextInt(),N=0;
            char a=sc.next().charAt(0);
            char[] p=FACT(n).toString().toCharArray();
            for(int i=0;i<p.length;i++){
                if(p[i]==a){
                    N++;
                }
            }
            System.out.println(N);
        }
        sc.close();
    }
}
