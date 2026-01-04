package Java.Luogu;

import java.util.Scanner;

public class 亲朋字符串 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String S=sc.next();
        char[] p=S.toCharArray(),q=new char[S.length()];
        for(int i=0;i<S.length()-1;i++){
            q[i]=(char)(p[i]+p[i+1]);
        }
        q[S.length()-1]=(char)(p[S.length()-1]+p[0]);
        System.out.println(new String(q));
        sc.close();
    }
}
