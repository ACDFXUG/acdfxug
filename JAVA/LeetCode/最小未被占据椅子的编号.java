package JAVA.LeetCode;

import java.util.*;

public class 最小未被占据椅子的编号 {
    static int smallestChair(int[][] times, int targetFriend) {
        PriorityQueue<Integer/*index*/> unoccupied=new PriorityQueue<>();
        HashMap<Integer/*i*/,Integer/*index*/> occupied=new HashMap<>();
        TreeMap<Integer/*time*/,Integer/*i*/> arrive=new TreeMap<>();
        HashMap<Integer/*time*/,List<Integer/*i*/>> leave=new HashMap<>();
        for(int i=0;i<times.length;++i){
            unoccupied.add(i);
            arrive.put(times[i][0],i);
            leave.computeIfAbsent(times[i][1],$->new ArrayList<>()).add(i);
        }
        for(int time=0;;++time){
            if(leave.containsKey(time)){
                leave.get(time).forEach(i->{
                    unoccupied.add(occupied.get(i));
                    occupied.remove(i);
                });
            }
            if(arrive.containsKey(time)){
                int i=arrive.get(time);
                occupied.put(i,unoccupied.poll());
                if(i==targetFriend) return occupied.get(i);
            }
        }
    }
    public static void main(String[] args) {
        int[][] times={
            {3,10},
            {1,5},
            {2,6}
        };
        System.out.println(smallestChair(times,0));
    }
}
