package JAVA.LeetCode;

import java.util.*;

public class 蜡烛之间的盘子 {
    static int[] platesBetweenCandles(String s, int[][] queries) {
        var plates=new TreeSet<Integer>(){{
            for(int i=0;i<s.length();i++)
                if(s.charAt(i)=='|') add(i);
        }};
        int[] ans=new int[queries.length];
        for(int i=0;i<queries.length;++i){
            var query=queries[i];
            var sub=plates.subSet(query[0],query[1]+1);
            if(sub.size()>1){
                ans[i]=sub.last()-sub.first()-1-(sub.size()-2);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        String s="***|**|*****|**||**|*";
        int[][] q={
            {1,17},
            {4,5},
            {14,17},
            {5,11},
            {15,16}
        };
        System.out.println(Arrays.toString(platesBetweenCandles(s,q)));
    }
}
