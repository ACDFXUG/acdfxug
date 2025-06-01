package JAVA.Luogu;

import java.util.Scanner;

public class 转8进制 {
    
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String BIN=sc.next();
        System.out.println(chgRADIX大数.toRadixSTR(chgRADIX大数.toDecSTR(BIN, 2), 8));
        sc.close();
    }
}
