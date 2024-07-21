package JAVA;

import java.util.Scanner;

interface OUT<typename>{
    void output(typename[] A,int N);
}

public class java21 {
    static Scanner sc=new Scanner(System.in);
    static OUT<Integer> OUTPUT=(ANS,len)->{
        for(int i=0;i<len;i++){
            System.out.println(ANS[i]);
        }
    };
    public static void main(String[] args) {
        int N=sc.nextInt();
        System.out.println(N);
    }
}
