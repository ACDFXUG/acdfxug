package Java.Luogu;

import java.util.Scanner;

public class 禽兽传染病 {
    static long pandamic(long x,long n){
        long ans=1;
        for(long i=1;i<=n;i++){
            ans+=ans*x;
        }
        return ans;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        long x=sc.nextLong(),n=sc.nextLong();
        System.out.println(pandamic(x, n));
        sc.close();
    }
}
