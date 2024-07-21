package Luogu;

import java.util.Scanner;

public class 查找相似字符串 {
    private static boolean similar(char[] p,char[] P){
        int L=p.length;
        for(int i=0;i<L;i++){
            if(p[i]!='*'){
                if(p[i]!=P[i]){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        char[] p=sc.next().toCharArray();
        int N=sc.nextInt(),t=0;
        String[] ANS=new String[N];
        for(int n=1;n<=N;n++){
            char[] P=sc.next().toCharArray();
            if(similar(p, P)){
                ANS[t++]=new String(P);
            }
        }
        for(int i=0;i<=t;i++){
            System.out.println(i==0?t:ANS[i-1]);
        }
        sc.close();
    }
}
