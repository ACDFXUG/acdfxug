package Java.LeetCode;

import java.util.*;

public class 最长数对链 {
    private static record Interval(int start,int end)
    implements Comparable<Interval>{
        public int compareTo(Interval itv){
            return end-itv.start;
        }
    }
    static int findLongestChain(int[][] pairs) {
        Interval[] itvs=new Interval[pairs.length];
        for(int i=0;i<pairs.length;i++){
            itvs[i]=new Interval(pairs[i][0],pairs[i][1]);
        }
        Arrays.sort(itvs);
        List<Interval> ans=new ArrayList<>();
        ans.add(itvs[0]);
        for(int i=1;i<itvs.length;i++){
            var itv=ans.getLast();
            if(itv.end<itvs[i].start){
                ans.add(itvs[i]);
            }
        }
        return ans.size();
    }
    public static void main(String[] args) {
        int[][] pairs={{1,2},{2,3},{3,4}};
        System.out.println(findLongestChain(pairs));
    }
}
