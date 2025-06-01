package JAVA.Luogu;

import java.util.*;

public class Guidebook {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        Map<Integer,Integer> scoreIndex=new HashMap<>();
        Map<Integer,String> city=new HashMap<>();
        for(int i=1;i<=N;i++){
            String name=sc.next();
            int score=sc.nextInt();
            scoreIndex.put(score,i);
            city.put(score,name);
        }
        sc.close();
        city.entrySet().stream()
            .sorted((E1,E2)->{
                int com=E1.getValue().compareTo(E2.getValue());
                return com==0?E2.getKey()-E1.getKey():com;
            }).forEach(E->System.out.println(scoreIndex.get(E.getKey())));
    }
}
