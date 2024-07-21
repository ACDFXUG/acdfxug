package Luogu;

import java.util.*;

public class 投票 {
    static String vote(String[] leader){
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < leader.length; i++){
            if(map.containsKey(leader[i])){
                map.put(leader[i], map.get(leader[i]) + 1);
            }else{
                map.put(leader[i], 1);
            }
        }
        int max = 0;
        for(int i = 0; i < leader.length; i++){
            if(map.get(leader[i]) > max){
                max = map.get(leader[i]);
            }
        }
        for(int i = 0; i < leader.length; i++){
            if(map.get(leader[i]) == max){
                return leader[i];
            }
        }
        return null;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N=sc.nextInt();
        String[] leader = new String[N];
        for(int i = 0; i < N; i++){
            leader[i] = sc.next();
        }
        System.out.println(vote(leader));
        sc.close();
    }
}