package Java.LeetCode;

import java.util.*;

public class 相对名次 {
    static String[] findRelativeRanks(int[] score) {
        Map<Integer,Integer> rank=new HashMap<>();
        for(int i=0;i<score.length;i++){
            rank.put(i,score[i]);
        }
        List<Integer> ranked=rank.entrySet().stream()
        .sorted((E1,E2)->E2.getValue()-E1.getValue())
        .map(Map.Entry::getKey)
        .toList();
        String[] ans=new String[score.length];
        for(int i=0;i<score.length;i++){
            int index=ranked.get(i);
            ans[index]=switch(i){
                case 0->"Gold Medal";
                case 1->"Silver Medal";
                case 2->"Bronze Medal";
                default->String.valueOf(i+1);
            };
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] score={10,3,8,9,4};
        System.out.println(Arrays.toString(findRelativeRanks(score)));
    }
}
