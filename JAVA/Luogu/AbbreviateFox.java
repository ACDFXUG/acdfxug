package Java.Luogu;

import java.util.Scanner;

public class AbbreviateFox {
    static final String FOX="fox";
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        String s=sc.next();
        while(s.contains(FOX)){
            s=s.replaceAll(FOX,"");
        }
        System.out.println(s.length()+N);
        sc.close();
    }
}
