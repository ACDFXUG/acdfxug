package Luogu;

import java.util.*;
import java.util.regex.*;

public class VJEKO {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        String s=sc.next();
        int star=s.indexOf("*");
        String l=s.substring(0,star);
        String r=s.substring(star+1);
        Pattern p=Pattern.compile(l+"([a-z]*)"+r);
        for(int i=0;i<N;i++){
            String str=sc.next();
            Matcher m=p.matcher(str);
            if(m.matches()){
                System.out.println("DA");
            }else{
                System.out.println("NE");
            }
        }
        sc.close();
    }
}
