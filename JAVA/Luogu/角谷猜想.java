package Java.Luogu;

import java.util.Scanner;

public class 角谷猜想 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        for(long n=sc.nextLong();true;){
            if(n%2==1&&n!=1){
                System.out.printf("%d*3+1=%d\n",n,3*n+1);
                n=n*3+1;
            }else if(n%2==0){
                System.out.printf("%d/2=%d\n",n,n/2);
                n/=2;
            }else if(n==1){
                System.out.println("End");
                break;
            }
        }
        sc.close();
    }
}
