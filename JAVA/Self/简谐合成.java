package Java.Self;

import java.util.Scanner;

public class 简谐合成 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        double A1=sc.nextDouble(),A2=sc.nextDouble(),
        degree1=sc.nextDouble(),degree2=sc.nextDouble();
        double A=Math.sqrt(A1*A1+A2*A2+2*A1*A2*Math.cos(degree1-degree2));
        double DEGREE=Math.atan((A1*Math.sin(degree1)+A2*Math.sin(degree2))
        /(A1*Math.cos(degree1)+A2*Math.cos(degree2)));
        System.out.println(A+" "+DEGREE);
        sc.close();
    }
}
