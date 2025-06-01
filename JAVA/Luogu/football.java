package JAVA.Luogu;

import java.util.Scanner;

public class football {
    static String A="1111111",B="0000000";
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        if(s.length()<=6){
            System.out.println("NO");
        }else{
            if(s.contains(A)||s.contains(B)){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
        sc.close();
    }
}
