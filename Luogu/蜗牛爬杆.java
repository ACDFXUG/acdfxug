package Luogu;

import java.util.Scanner;

public class 蜗牛爬杆 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt(),b=sc.nextInt(),v=sc.nextInt();
        for(int n=(v-a)/(a-b);n<=v*(a-b);n++){
            if(n*(a-b)+a>=v&&n*(a-b)<v){
                System.out.println(n+1);
                break;
            }
        }
        sc.close();
    }
}
