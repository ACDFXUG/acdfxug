package Luogu;

import java.util.Scanner;

public class 高低位交换 {
    static String EXCHANGE(String NUM){  //NUM32位
        char[] p=NUM.toCharArray();
        String ex="";
        for(int i=0;i<32;i++){
            ex+=(i<16)?p[i+16]:p[i-16];
        }
        return ex;
    }
    static long POWER(long a,long x){
        long ans=1;
        for(int i=1;i<=x;i++){
            ans*=a;
        }
        return ans;
    }
    static String toDECSTR(String BIN){
        long DEC=0;
        char[] bin=BIN.toCharArray();
        for(int i=BIN.length()-1;i>=0;i--){
            if(bin[i]=='1'){
                DEC+=POWER(2,BIN.length()-1-i);
            }
        }
        return DEC+"";
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String bin=Integer.toBinaryString(Integer.parseUnsignedInt(sc.next()));
        int L=bin.length();
        for(int i=0;i<32-L;i++){
            bin="0"+bin;
        }
        String BIN=EXCHANGE(bin);
        System.out.println(toDECSTR(BIN));
        sc.close();
    }
}
