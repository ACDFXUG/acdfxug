package Luogu;

import java.util.*;

public class RoomLeader {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Map<String,Integer> scores=new HashMap<>();
        int n=sc.nextInt();
        while(n-->0){
            String name=sc.next();
            scores.put(name,100*sc.nextInt());
            scores.merge(name,-50*sc.nextInt(),Integer::sum);
            for(int i=0;i<5;i++){
                scores.merge(name,sc.nextInt(),Integer::sum);
            }
        }
        sc.close();
        System.out.println(
            scores.entrySet().stream()
            .max((E1,E2)->E1.getValue()-E2.getValue())
            .get().getKey());
    }
}
