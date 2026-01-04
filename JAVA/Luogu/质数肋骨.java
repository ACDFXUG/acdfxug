package Java.Luogu;

import java.util.Scanner;

public class 质数肋骨 {
    static boolean PRIME(int n){
        for(int i=2;i<n;i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }
    static String SPLIT(String NUM){
        char[] p=NUM.toCharArray(),q=new char[NUM.length()-1];
        for(int i=0;i<NUM.length()-1;i++){
            q[i]=p[i];
        }
        return new String(q);
    }
    static int POWER(int x,int n){
        int init=1;   
        for(int i=1;i<=n;i++){
            init*=x;
        }
        return init;
    }
    static boolean PRIMERIB(int i){
        int l=Integer.toString(i).length();
            String NUM=Integer.toString(i);
            for(int j=0;j<l;j++){
                if(!PRIME(Integer.parseInt(NUM))){
                    return false;
                }
                NUM=SPLIT(NUM);
            }
            return true;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for(int i=2*POWER(10,n-1);i<POWER(10, n);i++){
            if(PRIMERIB(i)){
                System.out.println(i);
            }
        }
        sc.close();
    }
}
