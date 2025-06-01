package JAVA.Luogu;

import java.util.Scanner;

public class Watermelon {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int w=sc.nextInt();
        if(w<=3){
            System.out.println("NO");
        }else{
            System.out.println((w&1)==0?"YES":"NO");
        }
        sc.close();
    }
}
