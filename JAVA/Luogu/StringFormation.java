package JAVA.Luogu;

import java.util.*;

public class StringFormation {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        StringBuilder S=new StringBuilder(sc.next());
        int Q=sc.nextInt();
        while(Q-->0){
            int Ti=sc.nextInt();
            if(Ti==1){
                S.reverse();
            }else if(Ti==2){
                int Fi=sc.nextInt();
                String Ci=sc.next();
                switch(Fi){
                    case 1->S.insert(0,Ci);
                    case 2->S.append(Ci);
                }
            }
        }
        System.out.println(S);
        sc.close();
    }
}
