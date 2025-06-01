package JAVA.LeetCode;

import java.util.*;

public class 所有球里面不同颜色的数目 {
    static int[] queryResults(int limit, int[][] queries) {
        var ballColor=new HashMap<Integer,Integer>(limit+1);
        var colors=new HashMap<Integer,Set<Integer>>();
        final int n=queries.length;
        int[] ans=new int[n];
        for(int i=0;i<n;i++){
            int idx=queries[i][0];
            int color=queries[i][1];
            if(ballColor.containsKey(idx)){
                var oldColor=ballColor.get(idx);
                var idxSet=colors.get(oldColor);
                idxSet.remove(idx);
                if(idxSet.isEmpty()){
                    colors.remove(oldColor);
                }
                ballColor.put(idx,color);
                colors.computeIfAbsent(color,$->new HashSet<>()).add(idx);
            }else{
                ballColor.put(idx,color);
                colors.computeIfAbsent(color,$->new HashSet<>()).add(idx);
            }
            ans[i]=colors.size();
        }
        return ans;
    }
    public static void main(String[] args) {
        int[][] queries={
            {1,4},{2,5},
            {1,3},{3,4}
        };
        System.out.println(Arrays.toString(queryResults(4,queries)));
    }
}
