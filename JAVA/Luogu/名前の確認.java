package JAVA.Luogu;

import java.util.Scanner;

public class 名前の確認 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String W=sc.next();sc.close();
        System.out.println(W.substring(0,1).toUpperCase()+
        W.substring(1).toLowerCase());
    }
}
