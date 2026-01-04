package Java.Luogu;

import java.util.Scanner;

public class 求一次函数解析式 {
    static long gcd(long a,long b){
        return b==0L?a:gcd(b,a%b);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int x1=sc.nextInt(),y1=sc.nextInt(),
            x2=sc.nextInt(),y2=sc.nextInt();
        int deltaX=x1-x2,deltaY=y1-y2,deltaZ=x1*y2-x2*y1;
        long gcdA=gcd(Math.abs(deltaY),Math.abs(deltaX));
        long gcdB=gcd(Math.abs(deltaZ),Math.abs(deltaX));
        long Aup=deltaY/gcdA,Adown=deltaX/gcdA;
        long Bup=deltaZ/gcdB,Bdown=deltaX/gcdB;
        String A,B;
        if(Aup>=0&&Adown>0||Aup<=0&&Adown<0){
            Aup=Math.abs(Aup);
            Adown=Math.abs(Adown);
            if(Aup==0L){
                A="";
            }else if(Adown==1L){
                A=Aup+"";
            }else{
                A=(Aup)+"/"+(Adown);
            }
        }else{
            if(Aup>0&&Adown<0){
                if(Adown==-1L){
                    A="-"+Aup;
                }else{
                    A="-"+(Aup)+"/"+(-Adown);
                }
            }else{
                if(Adown==1L){
                    A=Aup+"";
                }else{
                    A=(Aup)+"/"+Adown;
                }
            }
        }
        if(Bup>=0&&Bdown>0||Bup<=0&&Bdown<0){
            Bup=Math.abs(Bup);
            Bdown=Math.abs(Bdown);
            if(Bup==0L){
                B="";
            }else if(Bdown==1L){
                B=Bup+"";
            }else{
                B=Bup+"/"+Bdown;
            }
        }else{
            if(Bup>0&&Bdown<0){
                if(Bdown==-1L){
                    B="-"+Bup;
                }else{
                    B="-"+(Bup)+"/"+(-Bdown);
                }
            }else{
                if(Bdown==1L){
                    B=Bup+"";
                }else{
                    B=(Bup)+"/"+Bdown;
                }
            }
        }
        if(B.equals("0")){
            if(A.contains("/")){
                System.out.println("y="+A+"*x");
            }else{
                System.out.println("y="+A+"x");
            }
        }else{
            if(A.contains("/")){
                System.out.println("y="+A+"*x+"+B);
            }else{
                System.out.println("y="+A+"x+"+B);
            }
        }
        sc.close();
    }
}
