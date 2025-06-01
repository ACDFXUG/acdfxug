package JAVA.LeetCode;

import java.util.Scanner;

public class IP地址无效化 {
    static String defangIPaddr(String address) {
        return address.replaceAll("\\.","[.]");
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String IP=sc.next();
        System.out.println(defangIPaddr(IP));
        sc.close();
    }
}
