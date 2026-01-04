package Java.Luogu;

import java.util.Scanner;

public class 吸烟问题 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        for(int s=1;s<=T;s++){
            int n=sc.nextInt(),k=sc.nextInt(),t;
        for(t=n;n>=k;n=n/k+n%k)
            t+=n/k;
        System.out.println(t);    
        }
        sc.close();
    }
}
