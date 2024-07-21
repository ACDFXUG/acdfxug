package Luogu;

import java.util.Scanner;

public class KidsRiddle {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),t=0;
        String hex=Integer.toHexString(n).toUpperCase();
        for(char x:hex.toCharArray()){
            switch(x){
                case'0','4','6','9','A','D'->++t;
                case'8','B'->t+=2;
            }
        }
        System.out.println(t);
        sc.close();
    }
}
