package Java.Luogu;

import java.math.BigInteger;
import java.util.Scanner;

public class 回文数P1015 {
    static String POWER(int a,int x){
        BigInteger pow=new BigInteger("1");
        for(int i=1;i<=x;i++){
            pow=pow.multiply(new BigInteger(""+a));
        }
        return pow+"";
    }
    static boolean Palindromes(String s){
        char[] p=s.toCharArray();
        for(int i=0;i<s.length()/2;i++){
            if(p[i]!=p[s.length()-1-i]){
                return false;
            }
        }
        return true;
    }
    static String reverse(String s){
        char[] p=s.toCharArray();
        String rev="";
        for(char x:p){
            rev=x+rev;
        }
        return rev;
    }
    static int position(char p){
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
    static String toDecSTR(String M,int N){
        char[] ANS=M.toCharArray();
        BigInteger change=new BigInteger("0");
        for(int i=0;i<M.length();i++){
            change=change.add(new BigInteger(position(ANS[i])+"").
            multiply(new BigInteger(POWER(N, M.length()-i-1))));
        }
        return change+"";
    }
    static String toRadixSTR(String M,int N){
        String ans="";
        char[] d="0123456789ABCDEF".toCharArray();
        BigInteger m=new BigInteger(M);
        BigInteger R=new BigInteger(N+"");
        for(;m.compareTo(new BigInteger("0"))==1;
        m=m.divide(R)){
            int r=Integer.parseInt(m.mod(R)+"");
            ans=d[r]+ans;
        }
        return ans;
    }
    static String r_plus(String M,int N){
        BigInteger plus=new BigInteger(toDecSTR(M, N)).
        add(new BigInteger(toDecSTR(reverse(M), N)));
        return toRadixSTR(plus+"", N);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),t;
        String M=sc.next();
        sc.close();
        for(t=1;true;){
            if(!Palindromes(r_plus(M, N))){
                M=r_plus(M, N);t++;
            }
            if(t>30||Palindromes(r_plus(M, N))){
                break;
            }
        }
        System.out.println(t<=30?"STEP="+t:"Impossible!");      
    }
}
