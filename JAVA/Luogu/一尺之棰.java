package JAVA.Luogu;

import java.util.Scanner;

public class 一尺之棰 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int t=1;
        for(int a=sc.nextInt();a>1;a/=2,t++);
        System.out.println(t);
        sc.close();
    }
}
