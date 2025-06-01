package JAVA.Luogu;

import java.util.*;

public class 头像上传 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),
            L=sc.nextInt(),
            G=sc.nextInt();
        while(n-->0){
            int Wi=sc.nextInt(),Hi=sc.nextInt();
            while(Wi>G||Hi>G){
                Wi>>=1;
                Hi>>=1;
            }
            if(Wi<L||Hi<L){
                System.out.println("Too Young");
                continue;
            }
            if(Wi!=Hi){
                System.out.println("Too Simple");
                continue;
            }else{
                System.out.println("Sometimes Naive");
                continue;
            }
        }
        sc.close();
    }
}
