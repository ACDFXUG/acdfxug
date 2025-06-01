package JAVA.LeetCode;

import java.util.*;

public class 基于时间的键值存储 {
    private static class TimeMap{
        Map<String,Map<Integer,String>> time;
        TimeMap(){
            this.time=new HashMap<
                String,Map<Integer,String>
            >();
        }
        void set(String key, String value, int timestamp) {
            time.computeIfAbsent(key,$->
                new TreeMap<>((a,b)->b-a)
            ).put(timestamp,value);
        }
        String get(String key, int timestamp) {
            var tm=time.get(key);
            if(tm==null){
                return "";
            }
            for(var entry:tm.entrySet()){
                if(entry.getKey()<=timestamp){
                    return entry.getValue();
                }
            }
            return "";
        }
    }
    public static void main(String[] args) {
        TimeMap tm=new TimeMap();
        tm.set("foo","bar",1);
        System.out.println(tm.get("foo",1));
        System.out.println(tm.get("foo",3));
        tm.set("foo","bar2",4);
        System.out.println(tm.get("foo",4));
        System.out.println(tm.get("foo",5));
    }
}
