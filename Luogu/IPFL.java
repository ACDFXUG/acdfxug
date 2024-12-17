package Luogu;

import java.util.Scanner;

public class IPFL {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        final int N=sc.nextInt();
        StringBuilder str=new StringBuilder(sc.next());
        final int Q=sc.nextInt();
        for(int i=0;i<Q;i++){
            int Ti=sc.nextInt(),
                Ai=sc.nextInt(),
                Bi=sc.nextInt();
            switch(Ti){
                case 1->{
                    char ai=str.charAt(Ai-1);
                    char bi=str.charAt(Bi-1);
                    str.setCharAt(Ai-1,bi);
                    str.setCharAt(Bi-1,ai);
                }case 2->{
                    var n=str.subSequence(0,N);
                    str.insert(2*N,n).delete(0,N);
                }
            }
        }
        System.out.println(str);
        sc.close();
    }
}
