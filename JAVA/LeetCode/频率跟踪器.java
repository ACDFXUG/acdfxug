package Java.LeetCode;

import java.util.*;

public class 频率跟踪器 {
    private static class FrequencyTracker {
        Map<Integer,Integer> nums;
        Map<Integer,Set<Integer>> freqSet;
        FrequencyTracker(){
            this.nums=new HashMap<>();
            this.freqSet=new HashMap<>();
        }
        void add(int number) {
            nums.merge(number,1,Integer::sum);
            int freqNum=nums.get(number);
            if(freqNum==1){
                freqSet.computeIfAbsent(freqNum,_->new HashSet<>()).add(number);
            }else{
                var preSet=freqSet.get(freqNum-1);
                preSet.remove(number);
                if(preSet.isEmpty()){
                    freqSet.remove(freqNum-1);
                }
                freqSet.computeIfAbsent(freqNum,_->new HashSet<>()).add(number);
            }
        }
        void deleteOne(int number) {
            if(!nums.containsKey(number)){
                return;
            }
            int freqNum=nums.get(number);
            if(freqNum==1){
                nums.remove(number);
                var preSet=freqSet.get(freqNum);
                preSet.remove(number);
                if(preSet.isEmpty()){
                    freqSet.remove(freqNum);
                }
            }else{
                nums.put(number,freqNum-1);
                var preSet=freqSet.get(freqNum);
                preSet.remove(number);
                if(preSet.isEmpty()){
                    freqSet.remove(freqNum);
                }
                freqSet.computeIfAbsent(freqNum-1,_->new HashSet<>()).add(number);
            }
        }
        boolean hasFrequency(int frequency) {
            return freqSet.containsKey(frequency);
        }
    }
    public static void main(String[] args) {
        FrequencyTracker ft=new FrequencyTracker();
        ft.add(5);
        ft.add(4);
        ft.deleteOne(6);
        ft.deleteOne(4);
        ft.deleteOne(7);
        System.out.println(ft.hasFrequency(1));
    }
}
