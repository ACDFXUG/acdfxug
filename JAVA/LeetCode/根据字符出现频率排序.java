package Java.LeetCode;

import java.util.*;
import java.util.Map.Entry;

public class 根据字符出现频率排序 {
    static String frequencySort(String s) {
        Map<Character,Integer> freq=new HashMap<>();
        for(char x:s.toCharArray()){
            if(freq.containsKey(x)){
                freq.put(x,freq.get(x)+1);
            }else{
                freq.put(x,1);
            }
        }
        List<Entry<Character,Integer>> list = new ArrayList<>(freq.entrySet());
        list.sort((x,y)->y.getValue()-x.getValue());
        StringBuilder sb=new StringBuilder();
        for(Entry<Character,Integer> entry:list){
            for(int i=0;i<entry.getValue();i++){
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String s="tree";
        System.out.println(frequencySort(s));
    }
}
