package Luogu;

import java.util.*;

public class medusa眼红 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        Map<Integer,Integer> freq=new TreeMap<>();
        for(int i=0;i<n;i++){
            int x=sc.nextInt();
            freq.put(i,x);
        }
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<m;i++){
            int x=sc.nextInt();
            list.add(x);
        }
        freq.entrySet().stream()
            .filter(E->list.contains(E.getValue()))
            .map(Map.Entry::getValue)
            .forEach(I->System.out.print(I+" "));
        System.out.println();
        sc.close();
    }
}
