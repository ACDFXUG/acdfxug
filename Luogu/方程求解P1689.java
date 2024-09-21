package Luogu;

import java.util.Scanner;

public class 方程求解P1689 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String equation=sc.nextLine().replaceAll("\\s+","");
        int plus=equation.indexOf("+");
        int sign=plus==-1?equation.indexOf("-"):plus;
        int equals=equation.indexOf("=");
        String X=equation.substring(0, sign);
        String Y=equation.substring(sign+1, equals);
        String Z=equation.substring(equals+1);
        if(equation.charAt(sign)=='+'){
            if(X.equals("?")){
                System.out.println(Integer.parseInt(Z)-Integer.parseInt(Y));
            }else if(Y.equals("?")){
                System.out.println(Integer.parseInt(Z)-Integer.parseInt(X));
            }else{
                System.out.println(Integer.parseInt(X)+Integer.parseInt(Y));
            }
        }else{
            if(X.equals("?")){
                System.out.println(Integer.parseInt(Z)+Integer.parseInt(Y));
            }else if(Y.equals("?")){
                System.out.println(Integer.parseInt(X)-Integer.parseInt(Z));
            }else{
                System.out.println(Integer.parseInt(X)-Integer.parseInt(Y));
            }
        }
        sc.close();
    }
}
