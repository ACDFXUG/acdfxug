package TJU;

import java.util.TreeSet;
import java.util.Scanner;

public class AaddB {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        for(;sc.hasNextInt();){
            int m=sc.nextInt(),n=sc.nextInt();
            if(m==0&&n==0){
                sc.close();
                break;
            }
            TreeSet<Integer> A=new TreeSet<>(Integer::compare)
                            ,B=new TreeSet<>(Integer::compare);
            for(int i=0;i<m;i++){
                A.add(sc.nextInt());
            }
            for(int i=0;i<n;i++){
                B.add(sc.nextInt());
            }
            A.addAll(B);
            A.forEach(i->System.out.print(i+" "));
            System.out.println();
        }
    }
}
