package Java.LeetCode;

import java.util.*;
import java.util.function.BiFunction;

public class LFU {
    static class LFUCache {
        static final BiFunction<Integer,Integer,Integer> ADD=(x,y)->x+y;
        final HashMap<Integer,Integer> cache,useCnt,useTime;
        final int MAX_SIZE;
        int curTime=1;

        public LFUCache(int capacity) {
            this.MAX_SIZE=capacity;
            this.cache=new HashMap<>(capacity);
            this.useCnt=new HashMap<>();
            this.useTime=new HashMap<>();
        }
        
        public int get(int key) {
            if(cache.containsKey(key)){
                useCnt.merge(key,1,ADD);
                useTime.replace(key,curTime++);
                return cache.get(key);
            }else{
                return -1;
            }
        }
        
        public void put(int key, int value) {
            if(cache.containsKey(key)){
                cache.replace(key,value);
                useCnt.merge(key,1,ADD);
                useTime.replace(key,curTime++);
            }else{
                if(cache.size()<MAX_SIZE){
                    cache.put(key,value);
                    useCnt.merge(key,1,ADD);
                    useTime.put(key,curTime++);
                }else{
                    useCnt.entrySet().stream()
                        .sorted((e1,e2)->{
                            if(e1.getValue()!=e2.getValue()){
                                return e1.getValue()-e2.getValue();
                            }else{
                                return useTime.get(e1.getKey())-useTime.get(e2.getKey());
                            }
                        }).findFirst()
                        .ifPresent(E->{
                            int K=E.getKey();
                            cache.remove(K);
                            useCnt.remove(K);
                            useTime.remove(K);
                        });
                    cache.put(key,value);
                    useCnt.merge(key,1,ADD);
                    useTime.put(key,curTime++);
                }
            }
        }
    }
    public static void main(String[] args) {
        LFUCache lfu=new LFUCache(2);
        lfu.put(1, 1);
        lfu.put(2, 2);
        System.out.println(lfu.get(1));
        lfu.put(3, 3);
        System.out.println(lfu.get(2));
        System.out.println(lfu.get(3));
        lfu.put(4, 4);
        System.out.println(lfu.get(1));
        System.out.println(lfu.get(3));
        System.out.println(lfu.get(4));

    }
}
