package Luogu;

import java.util.Scanner;

public class 探索未知 {
    static final class Fraction
    implements Comparable<Fraction>{
        int up,low;
        Fraction(){
            this.up=0;
            this.low=1;
        }
        Fraction(int up,int low){
            if(up<0&&low<0){
                up=-up;
                low=-low;
            }
            this.up=up;
            this.low=low;
        }
        static int GCD(int a,int b){
            int shift=0;
            if(a<b){
                a^=b;
                b^=a;
                a^=b;
            }
            if(b==0){
                return 0;
            }
            for(;a!=b;){
                if((a&1)==1){
                    if((b&1)==1){
                        b=(a-b)>>1;
                        a-=b;
                    }else{
                        b>>=1;
                    }
                }else{
                    if((b&1)==1){
                        a>>=1;
                        if(a<b){
                            a^=b;
                            b^=a;
                            a^=b;
                        }
                    }else{
                        a>>=1;
                        b>>=1;
                        shift++;
                    }
                }
            }
            return a<<shift;
        }
        static int gcd(int a,int b){
            return GCD(Math.abs(a),Math.abs(b));
        }
        Fraction toLowest(){
            int gcd=gcd(up,low);
            return new Fraction(up/gcd,low/gcd);
        }
        Fraction add(Fraction y){
            return new Fraction(up*y.low+low*y.up,low*y.low)
            .toLowest();
        }
        Fraction subtract(Fraction y){
            return new Fraction(up*y.low-low*y.up,low*y.low)
            .toLowest();
        }
        public int compareTo(Fraction y)
        throws ArithmeticException{
            int UP=up*y.low-low*y.up;
            int LOW=low*y.low;
            if(LOW>0){
                return UP;
            }else if(LOW<0){
                return -UP;
            }else{
                throw new ArithmeticException("分母为0");
            }
        }
        public String toString(){
            if(up%low==0){
                return Integer.toString(up/low);
            }else{
                int gcd=gcd(up,low);
                Fraction ZERO=new Fraction(0,1);
                if(compareTo(ZERO)>0){
                    if(gcd==1){
                        return up+"/"+low;
                    }else{
                        return up/gcd+"/"+low/gcd;
                    }
                }else{
                    int UP=Math.abs(up);
                    int LOW=Math.abs(low);
                    if(gcd==1){
                        return "-"+UP+"/"+LOW;
                    }else{
                        return "-"+UP/gcd+"/"+LOW/gcd;
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Fraction ans=new Fraction();
        int n=sc.nextInt();
        for(int i=0;i<n;i++){
            int up=sc.nextInt(),
                low=sc.nextInt(),
                act=sc.nextInt();
            switch(act){
                case 1->ans=ans.add(new Fraction(up,low));
                case 2->ans=ans.subtract(new Fraction(up,low));
            }
        }
        System.out.println(ans);
        sc.close();
    }
}
