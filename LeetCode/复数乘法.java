package LeetCode;

import java.util.regex.*;

public class 复数乘法 {
    static String imaginary="([+-]?\\d+)\\+([+-]?\\d+)i";
    static Pattern IMAGINARY=Pattern.compile(imaginary);
    static String complexNumberMultiply(String num1, String num2) {
        Matcher z1=IMAGINARY.matcher(num1),
                z2=IMAGINARY.matcher(num2);
        if(z1.matches()&&z2.matches()){
            int a=Integer.parseInt(z1.group(1)),
                b=Integer.parseInt(z1.group(2)),
                c=Integer.parseInt(z2.group(1)),
                d=Integer.parseInt(z2.group(2));
            return (a*c-b*d)+"+"+(a*d+b*c)+"i"; 
        }else{
            return null;
        }
        // int a=Integer.parseInt(num1.substring(0,num1.indexOf("+")));
        // int b=Integer.parseInt(num1.substring(num1.indexOf("+")+1,num1.length()-1));
        // int c=Integer.parseInt(num2.substring(0,num2.indexOf("+")));
        // int d=Integer.parseInt(num2.substring(num2.indexOf("+")+1,num2.length()-1));
        // return (a*c-b*d)+"+"+(a*d+b*c)+"i";
    }
    public static void main(String[] args) {
        String z1="1+-1i",z2="1+-1i";
        System.out.println(complexNumberMultiply(z1,z2));
    }
}
