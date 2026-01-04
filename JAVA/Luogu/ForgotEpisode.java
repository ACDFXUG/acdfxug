package Java.Luogu;

import java.util.Scanner;
import java.util.TreeSet;

public class ForgotEpisode {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        TreeSet<Integer> set=new TreeSet<>(Integer::compareTo);
        TreeSet<Integer> set2=new TreeSet<>(Integer::compareTo);
        for(int i=1;i<=N;i++){
            set.add(i);
        }
        for(int i=1;i<N;i++){
            set2.add(sc.nextInt());
        }
        set.removeAll(set2);
        System.err.println(set.getFirst());
        sc.close();
    }   
}
