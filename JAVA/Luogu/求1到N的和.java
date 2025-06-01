package JAVA.Luogu;

import java.util.Scanner;

public class 求1到N的和 {
    static int SUM(int N){
        return N==1?1:N+SUM(N-1);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println(SUM(sc.nextInt()));
        sc.close();
    }
}
