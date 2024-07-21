package JAVA;

import java.util.Scanner;

public class transform {
    static String REPLACE(String S,String s,int a,int b){
        char[] p=S.toCharArray();
        for(int i=a;i<=b;i++){
            p[i]=s.charAt(i-a);
        }
        return new String(p);
    }
    static String REVERSE(String S,int a,int b){
        char[] p=S.toCharArray();
        for(int i=a;i<b;i++){
            char c=p[i];
            p[i]=p[b+a-i];
            p[b+a-i]=c;
        }
        return new String(p);
    }
    static void PRINT(String S,int a,int b){
        System.out.println(S.substring(a, b+1));
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String S=sc.next();
        int q=sc.nextInt();
        for(int i=1;i<=q;i++){
            String com=sc.next();
            if(com.equals("replace")){
                int a=sc.nextInt(),b=sc.nextInt();
                String s=sc.next();
                S=REPLACE(S, s, a, b);
            }else if(com.equals("reverse")){
                int a=sc.nextInt(),b=sc.nextInt();
                S=REVERSE(S, a, b);
            }else if(com.equals("print")){
                int a=sc.nextInt(),b=sc.nextInt();
                PRINT(S, a, b);
            }
        }
        sc.close();
    }
}
