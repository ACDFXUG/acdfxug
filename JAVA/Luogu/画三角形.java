package JAVA.Luogu;

import java.util.Scanner;

public class 画三角形 {
    static int SUM(int i){
        int n=0;
        for(int k=1;k<i;k++){
            n+=k;
        }
        return n;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.printf(j==i?"%s\n":"%s",(char)((SUM(i)+j-1)%26+'A'));
            }
        }
        sc.close();
    }
}
