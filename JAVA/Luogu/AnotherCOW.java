package JAVA.Luogu;

import java.util.Scanner;

public class AnotherCOW {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        for(int N=sc.nextInt(),score=0;;score++){
            if(N%2==1&&N!=1){
                N=3*N+1;
            }else if(N%2==0){
                N/=2;
            }else if(N==1){
                System.out.println(score);
                break;
            }
        }
        sc.close();
    }
}
