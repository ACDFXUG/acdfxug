package JAVA.Luogu;

import java.util.Scanner;

public class digitSums {
    static int S(int n){
        int ans=0;
        for(char x:String.valueOf(n).toCharArray()){
            ans+=x-'0';
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(n%S(n)==0?"Yes":"No");
        sc.close();
    }
}
