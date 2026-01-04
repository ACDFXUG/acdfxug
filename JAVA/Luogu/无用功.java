package Java.Luogu;

import java.util.Scanner;

public class 无用功 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),A[]=new int[100001],t=0;
        for(int x;N>0;N--){
            x=sc.nextInt();
            A[x]++;
        }
        for(int x:A){
            if(x>=2){
                t+=x-1;
            }
        }
        System.out.println(t);
        sc.close();
    }
}
