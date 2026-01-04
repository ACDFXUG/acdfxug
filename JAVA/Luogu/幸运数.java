package Java.Luogu;

import java.util.Scanner;

public class 幸运数 {
    static int trans(int N){
        if(N<10){
            return N;
        }else{
            char[] p=Integer.toString(N).toCharArray();
            int M=p[0]-'0'+p[1]-'0';
            return trans(M);
        }
    }
    static boolean digitsum(int n){
        char[] p=Integer.toString(n).toCharArray();
        int sum=0;
        for(int i=0;i<p.length;i++){
            sum+=p[i]-'0';
        }
        return sum%8==0?true:false;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        for(int i=1;i<=N;i++){
            int NUM=sc.nextInt();
            char[] p=Integer.toString(NUM).toCharArray();
            for(int j=0;2*j<p.length;j++){
                p[2*j]=(char)(trans(7*(p[2*j]-'0'))+'0');
            }
            NUM=Integer.parseInt(new String(p));
            System.out.println(digitsum(NUM)?"T":"F");
        }
        sc.close();
    }
}
