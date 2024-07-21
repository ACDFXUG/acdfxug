package Luogu;

import java.util.Scanner;

public class StoneMonument {
    static int height(int i){
        int ans=0;
        for(int s=1;s<=i;ans+=s++);
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt(),b=sc.nextInt();
        System.out.println(height(b-a)-b);
        sc.close();
    }
}
