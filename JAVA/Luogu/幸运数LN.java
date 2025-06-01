package JAVA.Luogu;

import java.util.Scanner;

public class 幸运数LN {
    static int trans(int N){
        if(N<10){
            return N;
        }else{
            char[] p=Integer.toString(N).toCharArray();
            int M=p[0]-'0'+p[1]-'0';
            return trans(M);
        }
    }
    static boolean digitsum(String N){
        char[] p=N.toCharArray();
        int sum=0;
        for(int i=0;i<p.length;i++){
            sum+=p[i]-'0';
        }
        return sum%8==0?true:false;
    }
    static String reverse(String s){
        String ans="";
        char[] p=s.toCharArray();
        for(int i=0;i<p.length;i++){
            ans=p[i]+ans;
        }
        return ans;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        for(int i=1;i<=N;i++){
            String NUM=reverse(sc.next());
            char[] p=NUM.toCharArray();
            for(int j=0;2*j<p.length;j++){
                p[2*j]=(char)(trans(7*(p[2*j]-'0'))+'0');
            }
            NUM=reverse(new String(p));
            System.out.println(digitsum(NUM)?"T":"F");
        }
        sc.close();
    }
}
