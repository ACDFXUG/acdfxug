package JAVA.Luogu;

import java.util.*;

public class 共通要素 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Set<Integer> freq1=new TreeSet<>();
        Set<Integer> freq2=new TreeSet<>();
        int N=sc.nextInt(),M=sc.nextInt();
        while(N-->0){
            freq1.add(sc.nextInt());
        }
        while(M-->0){
            freq2.add(sc.nextInt());
        }
        freq1.retainAll(freq2);
        freq1.forEach(System.out::println);
        sc.close();
    }
}
