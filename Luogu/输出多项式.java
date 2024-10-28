package Luogu;

import java.util.Scanner;
import java.util.StringJoiner;

public class 输出多项式 {
    static String xPow(int coeff,int pow){
        if(pow==0){
            return String.valueOf(coeff);
        }else if(pow==1){
            return switch(coeff){
                case 1->"x";
                case -1->"-x";
                default->coeff+"x";
            };
        }else{
            return switch(coeff){
                case 1->"x^"+pow;
                case -1->"-x^"+pow;
                default->coeff+"x^"+pow;
            };
        }
    }
    static String polynomial(int... coeff){
        if(coeff.length==1){
            return String.valueOf(coeff[0]);
            
        }
        StringJoiner poly=new StringJoiner("+");
        for(int i=0,l=coeff.length;i<l;i++){
            if(coeff[i]!=0){
                poly.add(xPow(coeff[i],l-i-1));
            }else{
                if(i==l-1){
                    poly.add("0");
                }else{
                    continue;
                }
            }
        }
        return poly.toString().replaceAll("\\+-","-")
        .replaceAll("\\+0","");
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        // int n=sc.nextInt();
        // int[] a=new int[n+1];
        // for(int i=0;i<=n;i++){
        //     a[i]=sc.nextInt();
        // }
        System.out.println(polynomial(1,0,1));
        sc.close();
    }
}
