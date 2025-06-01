package JAVA.Luogu;

import java.util.Scanner;

public class 因子 {
    static int FACTOR(int n){
        int factor=0;
        for(int k=1;k<=n;k++){
            if(n%k==0){
                factor++;
            }
        }
        return factor;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for(int k=1;true;k++){
            if(n==FACTOR(k)){
                System.out.println(k);
                break;
            }
        }
        sc.close();
    }
}
