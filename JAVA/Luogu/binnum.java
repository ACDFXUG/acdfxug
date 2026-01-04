package Java.Luogu;

import java.util.Scanner;
import java.math.BigInteger;

public class binnum {
    static BigInteger BinToDec(String bin){
        BigInteger dec=new BigInteger("0");int L=bin.length();
        for(int i=0;i<L;i++){
            dec=dec.add(bin.charAt(i)=='1'?new BigInteger((1<<(L-1-i))+""):
            new BigInteger("0"));
        }
        return dec;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String bin=sc.next();sc.close();int t=0;
        BigInteger dec=BinToDec(bin),one=new BigInteger("1");
        for(;!dec.equals(one);t++){
            if(dec.and(one).equals(one)){
                dec=dec.add(one);
            }else{
                dec=dec.shiftRight(1);
            }
        }
        System.out.println(t);
    }
}
