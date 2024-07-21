package Luogu;

import java.util.Scanner;

public class 公约公倍数 {
    static int gcd(int P,int Q){
        return Q>0?gcd(Q,P%Q):P;
    }
    static int lcm(int P,int Q){
        return P*Q/gcd(P, Q);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int x=sc.nextInt(),y=sc.nextInt(),t=0;
        for(int P=1;P<=x*y;P++){
            for(int Q=P;Q<=x*y;Q++){
                if(gcd(P, Q)==x&&lcm(P, Q)==y){
                    t++;
                }
            }
        }
        System.out.println(t*2);
        sc.close();
    }
}
