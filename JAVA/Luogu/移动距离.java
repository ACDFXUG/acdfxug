package JAVA.Luogu;

import java.util.Scanner;

public class 移动距离 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int w=sc.nextInt(),m=sc.nextInt(),n=sc.nextInt(),
        Ym=(m-1)/w,Xm=Ym%2==0?(m-Ym*w-1):w-(m-Ym*w-1)-1,
        Yn=(n-1)/w,Xn=Yn%2==0?(n-Yn*w-1):w-(n-Yn*w-1)-1;
        System.out.println(Math.abs(Xm-Xn)+Math.abs(Ym-Yn));
        sc.close();
    }
}
