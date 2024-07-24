package LeetCode;

import java.util.*;

public class 删除字符使频率相同 {
    static boolean equalFrequency(String word) {
        Map<Character,Integer> freq=
        new HashMap<Character,Integer>(){{
            for(char x:word.toCharArray()){
                put(x,getOrDefault(x,0)+1);
            }
        }},FREQ;
        for(char i='a';i<='z';i++){
            if(freq.containsKey(i)){
                FREQ=new HashMap<>(freq);
                if(FREQ.get(i)==1){
                    FREQ.remove(i);
                }else{
                    FREQ.put(i,FREQ.get(i)-1);
                }
                // FREQ.forEach((C,I)->System.out.println(C+" "+I));
                // System.out.println();
                if(FREQ.values().stream().distinct().count()==1L)return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        String word="aazz";
        System.out.println(equalFrequency(word));
    }
}
