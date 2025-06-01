package JAVA.Luogu;

import java.util.Scanner;

public class digit函数 {
    static int digit(int n,int k){
        return Integer.toString(n).toCharArray()[Integer.toString(n).length()-k]-'0';
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),k=sc.nextInt();
        System.out.println(digit(n, k));
        sc.close();
    }
}
