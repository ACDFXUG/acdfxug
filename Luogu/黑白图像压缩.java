package Luogu;

import java.util.Scanner;

public class 黑白图像压缩 {
    static int POWER(int a, int x){
        int pow=1;
        for(int i=1;i<=x;i++){
            pow*=a;
        }
        return pow;
    }
    static String eight_bit(int n){
        String bit=Integer.toString(n, 2);
        for(int i=0;i<8-Integer.toString(n, 2).length();i++){
            bit="0"+bit;
        }
        return bit;
    }
    static String eight_bit_pixel(String s){
        int n=s.length();
        String BIN=Integer.toString(n, 2);
        for(int i=1;i<8-Integer.toString(n,2).length();i++){
            BIN="0"+BIN;
        }
        return s.contains("0")?"0"+BIN:"1"+BIN;
    }
    static String toDECSTR(String bin){
        int dec=0;
        char[] p=bin.toCharArray();
        for(int i=bin.length()-1;i>=0;i--){
            if(p[i]=='1'){
                dec+=POWER(2,bin.length()-1-i);
            }
        }
        return Integer.toString(dec);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        String pixel="";
        for(int v=0;v<n/8;v++){
            pixel+=eight_bit(sc.nextInt());
        }
        int L=pixel.length(),t=0;
        pixel+="#";
        char[] p=pixel.toCharArray();
        String[] PIXEL=new String[L];
        for(int i=0;i<p.length;i++){
            if(p[i]!=p[0]){
                char[] q=new char[i],P=new char[p.length-i];
                for(int j=0;j<i;j++){
                    q[j]=p[j];
                }
                for(int j=0;j<p.length-i;j++){
                    P[j]=p[j+i];
                }
                PIXEL[t++]=new String(q);
                p=P.clone();
                i=0;
            }
        }
        for(int i=0;i<t;i++){
            System.out.printf("%s ",toDECSTR(eight_bit_pixel(PIXEL[i])));
        }
        sc.close();
    }
}
