package JAVA.Luogu;

import java.util.*;

public class 顺序的分数 {
    static private class Fraction
    implements Comparable<Fraction>{
        int up,low;
        static int gcd(int a,int b){
            return b==0?a:gcd(b,a%b);
        }
        Fraction(int a,int b){
            this.up=a;
            this.low=b;
        }
        public int compareTo(Fraction y){
            return up*y.low-low*y.up;
        }
        public String toString(){
            int gcd=gcd(up,low);
            return up/gcd+"/"+low/gcd;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Set<Fraction> order=new TreeSet<Fraction>(){{
            for(int l=2;l<=n;l++){
                for(int u=1;u<l;u++){
                    add(new Fraction(u,l));
                }
            }
            add(new Fraction(0,1));
            add(new Fraction(1,1));
        }};
        order.forEach(System.out::println);
        sc.close();
    }
}
