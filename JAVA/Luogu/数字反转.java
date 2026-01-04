package Java.Luogu;

import java.util.Scanner;

public class 数字反转 {
    static char[] DELETE(char[] q,int i){
        char[] Q=new char[q.length-1];
        for(int s=0;s<q.length-1;s++){
            Q[s]=q[s+(s>=i?1:0)];
        }
        return Q;
    }
    static String REVERSE(int x){
        String X=Integer.toString(Math.abs(x));
        int L=X.length();
        char[] p=X.toCharArray(),q=new char[L];
        for(int i=0;i<L;i++){
            q[i]=p[L-1-i];
        }
        for(;q[0]=='0';q=DELETE(q, 0).clone());
        String S=new String(q);
        return x>0?S:("-"+S);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int x=sc.nextInt();
        System.out.println(x==0?x:REVERSE(x));
        sc.close();
    }
}
