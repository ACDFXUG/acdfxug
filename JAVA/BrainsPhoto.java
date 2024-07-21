package JAVA;

import java.util.Scanner;

public class BrainsPhoto {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        boolean color=false;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                char p=sc.next().charAt(0);
                if(p=='C'||p=='M'||p=='Y'){
                    color=true;
                }
            }
        }
        sc.close();
        System.out.println(color?"#Color":"#Black&White");
    }
}
