package Luogu;

import java.util.*;

public class 宇宙人からのメッセージ {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String S=sc.next();
        int L=S.length();
        StringBuilder T=new StringBuilder(L);
        for(int i=0;i<L;i++){
            char c=S.charAt(i);
            if(c=='R'){
                T.reverse();
            }else{
                int l=T.length();
                if(l>0){
                    if(T.charAt(l-1)==c){
                        T.deleteCharAt(l-1);
                    }else{
                        T.append(c);
                    }
                }else{
                    T.append(c);
                }
            }
        }
        System.out.println(T);
        sc.close();
    }
}
