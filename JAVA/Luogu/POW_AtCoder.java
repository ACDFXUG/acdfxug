package Java.Luogu;

import java.util.Scanner;

public class POW_AtCoder {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int A=sc.nextInt(),B=sc.nextInt(),
        C=sc.nextInt();
        if((C&1)==0){
            int absA=Math.abs(A),absB=Math.abs(B);
            if(absA>absB){
                System.out.println(">");
            }else if(absA==absB){
                System.out.println("=");
            }else{
                System.out.println("<");
            }
        }else{
            if(A>B){
                System.out.println(">");
            }else if(A==B){
                System.out.println("=");
            }else{
                System.out.println("<");
            }
        }
        sc.close();
    }
}
