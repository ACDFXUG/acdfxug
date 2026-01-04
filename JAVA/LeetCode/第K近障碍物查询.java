package Java.LeetCode;

import java.util.*;

public class 第K近障碍物查询 {
    static int[] resultsArray(int[][] queries, int k) {
        int[] result=new int[queries.length];
        Queue<Integer> closest=new PriorityQueue<>((a,b)->b-a);
        for(int i=0;i<queries.length;++i){
            int dis=Math.abs(queries[i][0])+Math.abs(queries[i][1]);
            if(closest.size()<k){
                closest.add(dis);
                result[i]=-1;
                if(closest.size()==k) result[i]=closest.peek();
                continue;
            }else if(dis<closest.peek()){
                closest.poll();
                closest.add(dis);
            }
            result[i]=closest.peek();
        }
        return result;
    }
    public static void main(String[] args) {
        int[][] q={
            {1,2},{3,4},
            {2,3},{-3,0}
        };
        System.out.println(Arrays.toString(resultsArray(q,2)));
    }
}
