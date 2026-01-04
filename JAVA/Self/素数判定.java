package Java.Self;

import java.util.Scanner;

public class 素数判定 {
    static boolean prime(int n){
        for(int i=2;i<n;i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNextInt()){
            int x=sc.nextInt(),y=sc.nextInt();
            if(x==0&&y==0){
                break;
            }else{
                int t=0;
                for(int n=x;n<=y;n++){
                    if(prime(n*n+n+41)){
                        t++;
                    }
                }
                if(t==y-x+1){
                    System.out.println("OK");
                }else{
                    System.out.println("Sorry");
                }
            }
        }
    sc.close();
    }
}
