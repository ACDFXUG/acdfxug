package LeetCode;

import java.util.*;
import java.util.Map.Entry;

public class LRU缓存 {
    private static class LRUCache{
        @SuppressWarnings("unused")
        private final int capacity;
        private final Map<Integer,Integer> lruCache;
        LRUCache(int capacity){
            this.capacity=capacity;
            this.lruCache=new LinkedHashMap<Integer,Integer>(capacity,0.75F,true){
                protected boolean removeEldestEntry(Entry<Integer,Integer> eldest){
                    return size()>capacity;
                }
            };
        }
        int get(int key){
            return lruCache.getOrDefault(key,-1);
        }
        void put(int key, int value) {
            lruCache.put(key,value);
        }
    }
    public static void main(String[] args) {
        LRUCache lru=new LRUCache(5);
        lru.put(1,1);
        lru.put(0,0);
        lru.get(-1);
    }
}
