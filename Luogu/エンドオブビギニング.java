package Luogu;

import java.util.Scanner;

public class エンドオブビギニング {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        for(String P,Q,R;N>0;N--){
            P=sc.next();
            Q=sc.next();
            R=sc.next();
            switch(P){
                case"BEGINNING":
                System.out.print(R.charAt(0));
                break;
                case"MIDDLE":
                System.out.print(R.charAt(R.length()>>1));
                break;
                case"END":
                System.out.print(R.charAt(R.length()-1));
                break;
                default:
                System.out.println(Q);
            }
        }
        System.out.println();
        sc.close();
    }
}
