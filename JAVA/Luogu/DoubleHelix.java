package JAVA.Luogu;

import java.util.Scanner;

public class DoubleHelix {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        char b=sc.next().charAt(0);sc.close();
        System.out.println(
            switch(b){
                case'A'->'T';
                case'T'->'A';
                case'C'->'G';
                case'G'->'C';
                default->'0';
            });
    }
}
