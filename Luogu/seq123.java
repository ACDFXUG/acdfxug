package Luogu;

import java.util.Scanner;

public class seq123 {
    static int max(int a,int b){
        return a>b?a:b;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] seq=new int[3];
        for(int i=n;i>0;i--){
            seq[sc.nextInt()-1]++;
        }
        int max=max(seq[0],max(seq[1],seq[2]));
        System.out.println(n-max);
        sc.close();
    }
}
