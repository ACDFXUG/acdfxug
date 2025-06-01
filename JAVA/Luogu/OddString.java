package JAVA.Luogu;

import java.util.Scanner;

public class OddString {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String S=sc.next();
        for(int i=0;i<S.length();i++){
            if((i&1)==0){
                System.out.print(S.charAt(i));
            }
        }
        System.out.println();
        sc.close();
    }
}
