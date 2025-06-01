package JAVA.Luogu;

import java.util.Scanner;

public class Bitpp {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int x=0,n=sc.nextInt();sc.close();
        for(;n>0;n--){
            String act=sc.next();
            switch(act){
                case "X++","++X"->x++;
                case "X--","--X"->x--;
            }
        }
        System.out.println(x);
    }
}
