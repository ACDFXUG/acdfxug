package Java.Luogu;

import java.util.*;

public class SortingQueries {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        List<Integer> A=new ArrayList<>();
        int Q=sc.nextInt();
        while(Q-->0){
            int act=sc.nextInt();
            switch(act){
                case 1->A.add(sc.nextInt());
                case 2->System.out.println(A.remove(0));
                case 3->A.sort(null);
            }
        }
        sc.close();
    }
}
