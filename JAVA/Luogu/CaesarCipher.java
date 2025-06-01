package JAVA.Luogu;

import java.util.Scanner;

public class CaesarCipher {
    static final String CIPHER=
    "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        int k=sc.nextInt();
        StringBuilder caesar=new StringBuilder();
        for(int i=0,l=str.length();i<l;i++){
            char c=str.charAt(i);
            caesar.append(CIPHER.charAt(c-'A'+k));
        }
        System.out.println(caesar);
        sc.close();
    }
}
