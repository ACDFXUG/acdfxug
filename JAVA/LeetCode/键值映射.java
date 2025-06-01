package JAVA.LeetCode;

import java.util.*;

public class 键值映射 {
    private static class MapSum{
        Map<String,Integer> mapSum;
        MapSum(){
            this.mapSum=new HashMap<String,Integer>();
        }
        void insert(String key, int val) {
            mapSum.put(key,val);
        }
        int sum(String prefix) {
            // return mapSum.entrySet().stream()
            // .filter(E->E.getKey().startsWith(prefix))
            // .mapToInt(Map.Entry::getValue)
            // .sum();
            int sum=0;
            for(Map.Entry<String,Integer> entry:mapSum.entrySet())
                if(entry.getKey().startsWith(prefix))
                    sum+=entry.getValue();
            return sum;
        }
    }
    public static void main(String[] args) {
        MapSum ms=new MapSum();
        ms.insert("apple",3);
        System.out.println(ms.sum("ap"));
        ms.insert("app",2);
        System.out.println(ms.sum("ap"));
    }
}
