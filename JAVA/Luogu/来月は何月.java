package Java.Luogu;

import java.util.Scanner;

public class 来月は何月 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        if(N>=1&&N<=11){
            System.out.println(N+1);
        }else{
            System.out.println(1);
        }
        sc.close();
    }
}
