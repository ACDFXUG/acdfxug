package Luogu;

import java.util.Scanner;

public class カキ {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int ans=0;
        for(int i=0;i<12;i++){
            if(sc.next().contains("r")){
                ans++;
            }
        }
        System.out.println(ans);
        sc.close();
    }
}
