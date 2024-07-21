package LeetCode;

import java.util.*;

public class 频率跟踪器 {
    private static class FrequencyTracker {
        Map<Integer,Integer> freq;
        FrequencyTracker(){
            this.freq=new HashMap<Integer,Integer>();
        }
        void add(int number){
            freq.put(number,freq.getOrDefault(number,0)+1);
        }
        void deleteOne(int number){
            if(freq.containsKey(number)){
                int count=freq.get(number);
                if(count==1){
                    freq.remove(number);
                }else{
                    freq.put(number,count-1);
                }
            }
        }
        boolean hasFrequency(int frequency){
            return freq.containsValue(frequency);
        }
    }
    public static void main(String[] args) {
        FrequencyTracker ft=new FrequencyTracker();
        ft.add(1);
        ft.add(1);
        ft.add(3);
        ft.deleteOne(1);
        System.out.println(ft.hasFrequency(2));
    }
}
