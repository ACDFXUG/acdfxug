package Java.Luogu;

import java.util.Scanner;

public class 配对数couplenumber {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt(),b=sc.nextInt(),CN=0;
        for(int n=a;n<=b;n++){
            int N=Math.abs(n);
            for(int x=0;x<N;x++){
                for(int y=0;y<=x;y++){
                    if(N==x*x-y*y){
                        CN++;
                    }
                }
            }
        }
        System.out.println(CN);
        sc.close();
    }
}
