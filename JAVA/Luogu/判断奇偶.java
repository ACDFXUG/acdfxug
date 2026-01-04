package Java.Luogu;

import java.util.Scanner;

public class 判断奇偶 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        for(int i=0;i<N;i++){
            String NUM=sc.next();
            char p=NUM.charAt(NUM.length()-1);
            if((p-'0')%2==1){
                System.out.println("odd");
            }else{
                System.out.println("even");
            }
        }
        sc.close();
    }
}
