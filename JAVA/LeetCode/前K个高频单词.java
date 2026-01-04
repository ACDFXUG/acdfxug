package Java.LeetCode;

import java.util.*;

public class 前K个高频单词 {
    static public List<String> topKFrequent(String[] words, int k) {
        Map<String,Integer> freq=new HashMap<>();
        for(String word:words){
            freq.put(word,freq.getOrDefault(word,0)+1);
        }
        return freq.entrySet().stream()
        .sorted((E1,E2)->{
            int freq1=E1.getValue();
            int freq2=E2.getValue();
            return freq1==freq2?
            E1.getKey().compareTo(E2.getKey()):freq2-freq1;
        })
        .map(Map.Entry::getKey)
        .limit(k).toList();
    }
}
