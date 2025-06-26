package JAVA.Luogu;

import java.util.*;

public class BreedCountingS {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),Q=sc.nextInt();
        List<int[]> cows=new ArrayList<>(3);
        int[] a=new int[N+1],b=new int[N+1],
            c=new int[N+1];
        cows.add(a);
        cows.add(b);
        cows.add(c);
        for(int cow,i=1;i<=N;++i){
            cow=sc.nextInt();
            switch(cow){
                case 1->{
                    a[i]=a[i-1]+1;
                    b[i]=b[i-1];
                    c[i]=c[i-1];
                }case 2->{
                    a[i]=a[i-1];
                    b[i]=b[i-1]+1;
                    c[i]=c[i-1];
                }case 3->{
                    a[i]=a[i-1];
                    b[i]=b[i-1];
                    c[i]=c[i-1]+1;
                }
            }
        }
        for(int A,B;Q-->0;){
            A=sc.nextInt();
            B=sc.nextInt();
            int dltA=a[B]-a[A-1];
            int dltB=b[B]-b[A-1];
            int dltC=c[B]-c[A-1];
            System.out.printf("%d %d %d\n",dltA,dltB,dltC);
        }
        sc.close();
    }
}
