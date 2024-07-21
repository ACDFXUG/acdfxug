package Luogu;

import java.util.Scanner;

public class 文字处理 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int q=sc.nextInt();
        String S=sc.next();
        for(int j=1;j<=q;j++){
            int act=sc.nextInt();
            if(act==1){
                String str=sc.next();
                S+=str;
                System.out.println(S);
            }else if(act==2){
                int a=sc.nextInt(),b=sc.nextInt();
                char[] p=new char[b];
                for(int i=0;i<b;i++){
                    p[i]=S.toCharArray()[i+a];
                }
                S=new String(p);
                System.out.println(S);
            }else if(act==3){
                int a=sc.nextInt();
                String str=sc.next();
                char[] p=new char[S.length()+str.length()];
                for(int i=0;i<a;i++){
                    p[i]=S.toCharArray()[i];
                }   
                for(int i=a;i<str.length()+a;i++){
                    p[i]=str.toCharArray()[i-a];
                }
                for(int i=a+str.length();i<S.length()+str.length();i++){
                    p[i]=S.toCharArray()[i-str.length()];
                }
                S=new String(p);
                System.out.println(S);
            }else if(act==4){
                String str=sc.next();
                System.out.println(S.indexOf(str));
            }
        }
        sc.close();
    }
}
