package Java.LeetCode;

import java.util.*;

public class 所有可能的路径 {
    static void backtrack(int[][] graph,List<List<Integer>> res,int start,List<Integer> path){
        path.add(start);
        if(start==graph.length-1){
            res.add(new ArrayList<>(path));
        }
        for(int next:graph[start]){
            backtrack(graph,res,next,path);
        }
        path.removeLast();
    }
    static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        backtrack(graph,res,0,path);
        return res;
    }
    public static void main(String[] args) {
        final int[][] graph={
            {4,3,1},
            {3,2,4},
            {3},
            {4},
            {}
        };
        System.out.println(allPathsSourceTarget(graph));
    }
}
