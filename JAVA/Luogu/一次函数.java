package Java.Luogu;

import java.util.Scanner;

public class 一次函数 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        for(int i=0;i<t;i++){
            int n=sc.nextInt();
            if(n==0){
                System.out.println(0);
            }else{
                if(n%2==0)
                System.out.printf("%d/%d\n",n/2,n+1);
                else
                System.out.printf("%d/%d\n",n,2*n+2);
            }
        }
        sc.close();
    }
}
