package JAVA.Luogu;

import java.util.Scanner;

public class Cifera {
    static int pow(int a,int x){
        int ans=1;
        for(;x>0;x--){
            ans*=a;
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int k=sc.nextInt(),l=sc.nextInt();
        double n=Math.log(l)/Math.log(k);
        boolean end=true;
        for(int i=(int)(n-1);i<=(int)(n+1);i++){
            if(pow(k, i)==l){
                System.out.println("YES\n"+(i-1));
                end=false;
                break;
            }
        }
        if(end){
            System.out.println("NO");
        }
        sc.close();
    }
}
