package JAVA.Luogu;

import java.util.*;

public class Hindeks {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        int[] a=new int[N];
        for(int i=0;i<N;a[i++]=sc.nextInt());
        Arrays.sort(a);
        sc.close();
    }
}
