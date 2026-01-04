package Java.Luogu;

import java.util.Scanner;

public class Majority {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),opinion=0;sc.close();
        for(;N>0;N--){
            switch(sc.next()){
                case"Against"->opinion--;
                case"For"->opinion++;
            }
        }
        System.out.println(opinion>0?"Yes":"No");
    }
}
