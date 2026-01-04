package Java.Luogu;

import java.util.Scanner;

public class Irrational {
    static int f(int i,int p1,int p2,int p3,int p4){
        return (((i%p1)%p2)%p3)%p4;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int p1=sc.nextInt(),p2=sc.nextInt(),p3=sc.nextInt(),p4=sc.nextInt();
        int a=sc.nextInt(),b=sc.nextInt(),t=0;
        for(int i=a;i<=b;i++){
            if(f(i, p1, p2, p3, p4)==i){
                t++;
            }
        }
        System.out.println(t);
        sc.close();
    }
}
