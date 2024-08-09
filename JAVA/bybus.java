package JAVA;

import java.util.*;

public class bybus {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("...");
        int a=0;
        for(int i=1;i<=10;i++){
            System.out.println("...");
            String b=sc.next();
            int c=sc.nextInt();
            if(b.equals("in")){
                a+=c;
            }else if(b.equals("out")){
                a-=c;
            }
        }
        System.out.println(a);
        sc.close();
    }
}
