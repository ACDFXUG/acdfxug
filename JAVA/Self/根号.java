package Java.Self;

import java.util.Scanner;
import java.math.*;

public class 根号 {
    static void sqrt(double p){
        double[] h=new double[100];
        h[99]=1;
        for(int i=98;i>=0;i--){
            h[i]=(h[i+1]+p/h[i+1])/2;
        }
        System.out.println(h[0]);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        double p=sc.nextDouble();
        BigDecimal P=BigDecimal.valueOf(p);
        System.out.println(P.sqrt(MathContext.DECIMAL128));
        sqrt(p);
        sc.close();
    }
}
