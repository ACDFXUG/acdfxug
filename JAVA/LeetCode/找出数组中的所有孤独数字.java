package Java.LeetCode;

import java.util.*;
import java.util.Map.Entry;

public class 找出数组中的所有孤独数字 {
    static List<Integer> findLonely(int[] nums) {
        Map<Integer, Integer> lonely=new HashMap<>();
        for(int x:nums){
            lonely.put(x,lonely.getOrDefault(x,0)+1);
        }
        List<Integer> alone=
        lonely.entrySet().stream()
        .filter(E->E.getValue()==1)
        .map(Entry::getKey)
        .toList(),ans=new ArrayList<>();
        for(Integer x:alone){
            if(!lonely.containsKey(x-1)&&
            !lonely.containsKey(x+1)){
                ans.add(x);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        
    }
}
