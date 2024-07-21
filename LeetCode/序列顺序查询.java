package LeetCode;

import java.util.*;
import java.util.Map.*;
import static java.util.AbstractMap.*;

public class 序列顺序查询 {
    private static class SORTracker{
        int index;
        Map<String,Integer> scenery;
        PriorityQueue<Entry<String,Integer>> pq;
        private Entry<String,Integer> getEntry(PriorityQueue<Entry<String,Integer>> pq,int index){
            PriorityQueue<Entry<String,Integer>> temp=
            new PriorityQueue<>(pq);
            while(index--!=0){
                temp.poll();
            }
            return temp.peek();
        }
        SORTracker(){
            this.index=0;
            this.scenery=new HashMap<String,Integer>();
            this.pq=new PriorityQueue<Entry<String,Integer>>((e1,e2)->e1.getValue()==e2.getValue()?
            e1.getKey().compareTo(e2.getKey()):e2.getValue()-e1.getValue());
        }
        void add(String name,int score){
            scenery.put(name,score);
            Entry<String,Integer> entry=new SimpleEntry<String,Integer>(name,score);
            pq.offer(entry);
        }
        String get(){
            return getEntry(pq, index++).getKey();
        }
    }
    public static void main(String[] args) {
        SORTracker sor=new SORTracker();
        sor.add("bradford",2);
        sor.add("branford",3);
        System.out.println(sor.get());
        sor.add("alps",2);
        System.out.println(sor.get());
        sor.add("orland",2);
        System.out.println(sor.get());
        sor.add("orlando",3);
        System.out.println(sor.get());
        sor.add("alpine",2);
        System.out.println(sor.get());
        System.out.println(sor.get());
    }
}
