package Java.Luogu;

import java.util.Scanner;

public class 翻转最大 {
    static int rev(int a){
        char[] p=(a+"").toCharArray();
        return p[0]-48+10*(p[1]-48)+100*(p[2]-48);
    }
    static void MAX(int a,int b){
        System.out.println(a>=b?a:b);
    }
   public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        MAX(rev(sc.nextInt()), rev(sc.nextInt()));
        sc.close();
    }
}
