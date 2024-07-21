package JAVA;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class LN {
    static BigDecimal power(BigDecimal x,int n){
        BigDecimal ans=BigDecimal.ONE;
        while(n>0){
            if(n%2==1){
                ans=ans.multiply(x);
            }
            x=x.multiply(x);
            n/=2;
        }
        return ans;
    }
    static BigDecimal ln(double x,int index,int scale){
        BigDecimal ans=BigDecimal.ZERO;
        if(x==1){
            return ans;
        }
        for(int k=0;k<=index;k++){
            BigDecimal SC=BigDecimal.valueOf(1.0/(2*k+1));
            BigDecimal INNER=BigDecimal.valueOf((x-1)/(x+1));
            ans=ans.add(SC.multiply(INNER.pow(2*k+1)));
        }
        return ans.multiply(BigDecimal.TWO).setScale(scale, RoundingMode.HALF_UP);
    }
    static BigDecimal ln(double x){
        return ln(x, 50, 20);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println(ln(2));
        sc.close();
    }
}
