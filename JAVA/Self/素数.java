package Java.Self;

import java.util.Scanner;

public class 素数 {
    static boolean PRIME(int n){   
        for(int i=2;i<n;i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        if(PRIME(N)){
            System.out.println(N+"是素数");
        }else{
            System.out.println(N+"不是素数");
        }
        sc.close();
    }
}
