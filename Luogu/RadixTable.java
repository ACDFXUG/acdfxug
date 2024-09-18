package Luogu;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringJoiner;

public class RadixTable {
    static final String HEX_RADIX="0123456789ABCDEF";
    static String hex(BigInteger x){
        StringBuilder hex=new StringBuilder();
        for(BigInteger $15=BigInteger.valueOf(15)
        ;x.compareTo(BigInteger.ZERO)>0;x=x.shiftRight(4)){
            int index=x.and($15).intValue();
            hex.append(HEX_RADIX.charAt(index));
        }
        return hex.append("x0").reverse().toString();
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.next();
        String[] number=str.length()<=2?null:
        str.substring(1,str.length()-1).split(",");
        StringJoiner ans=new StringJoiner(",","{","}");
        if(number!=null){
            for(String num:number){
                BigInteger x=new BigInteger(num);
                String HEX=hex(x);
                ans.add(HEX.length()<num.length()?HEX:num);
            }
        }
        System.out.println(ans);
        sc.close();
    }
}
