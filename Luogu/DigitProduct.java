package Luogu;

import java.util.Scanner;

public class DigitProduct {
    static int product(int X){
        int ans=1;
        while(X>0){
            int r=X%10;
            if(r!=0){
                ans*=r;
                X/=10;
            }else{
                return 0;
            }
        }
        return ans%1000000007;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        while(T-->0){
            long ans=1l;
            int l=sc.nextInt(),r=sc.nextInt();
            for(int i=l;i<=r;i++){
                int product=product(i);
                if(product!=0){
                    ans*=product;
                }else{
                    System.out.println("0");
                    break;
                }
            }
            System.out.println(ans%1_000_000_007);
        }
        sc.close();
    }
}
