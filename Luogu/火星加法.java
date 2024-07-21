package Luogu;

import java.util.Scanner;
import java.math.BigInteger;

public class 火星加法 {
    public static String POWER(int a,int x){
        BigInteger pow=new BigInteger("1");
        for(int i=1;i<=x;i++){
            pow=pow.multiply(new BigInteger(""+a));
        }
        return pow+"";
    }
    /**
     * 
     * @param p  要查找的字符
     * @return   p在"0123456789ABCDEFG"的位置，从0开始
     */
    public static int position(char p){
        if(p>='0'&&p<='9'){
            return (int)(p-48);
        }else if(p>='A'&&p<='Z'){
            return (int)(p-'A'+10);
        }else if(p>='a'&&p<='z'){
            return (int)(p-'a'+10);
        }else{
            return -1;
        }
    }
    /**
     * 
     * @param _str  需要转换的R进制字符串
     * @param _val  字符串的进制
     * @return   转换后的10进制字符串
     */
    public static String toDecSTR(String _str,int _val){
        char[] ANS=_str.toCharArray();
        BigInteger change=new BigInteger("0");
        for(int i=0;i<_str.length();i++){
            change=change.add(new BigInteger(position(ANS[i])+"").
            multiply(new BigInteger(POWER(_val, _str.length()-i-1))));
        }
        return change+"";
    }
    /**
     * 
     * @param _str  需要转换的字符串
     * @param _val  需要转换的进制
     * @return   转换后的R进制字符串
     */
    public static String toRadixSTR(String _str,int _val){
        String ans="";
        char[] d="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        BigInteger m=new BigInteger(_str);
        BigInteger Radix=new BigInteger(_val+"");
        for(;m.compareTo(new BigInteger("0"))==1;
        m=m.divide(Radix)){
            int r=Integer.parseInt(m.mod(Radix)+"");
            ans=d[r]+ans;
        }
        return ans;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        String s1=sc.next(),s2=sc.next();
        BigInteger S1=new BigInteger(toDecSTR(s1, N));
        BigInteger S2=new BigInteger(toDecSTR(s2, N));
        BigInteger ANS=S1.add(S2);
        System.out.println(toRadixSTR(ANS+"", N).toLowerCase());
        sc.close();
    }
}
