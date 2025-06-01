package JAVA.Luogu;

import java.util.*;

public class 枚举子集 {
    static void select(int n,List<Boolean> come){
        if(come.size()==n){
            for(int i=0;i<n;i++){
                System.out.printf(i==n-1?"%s\n":"%s",(come.get(i)?"Y":"N"));
            }
            return;
        }
        come.add(false);
        select(n,come);
        come.removeLast();

        come.add(true);
        select(n,come);
        come.removeLast();
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        select(n,new LinkedList<>());
        sc.close();
    }
}
