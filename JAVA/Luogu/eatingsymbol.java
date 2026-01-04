package Java.Luogu;

import java.util.Scanner;

public class eatingsymbol {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=0;
        String s=sc.next();
        for(char x:s.toCharArray()){
            switch(x){
                case'+'->t++;
                case'-'->t--;
            }
        }
        System.out.println(t);
        sc.close();
    }
}
