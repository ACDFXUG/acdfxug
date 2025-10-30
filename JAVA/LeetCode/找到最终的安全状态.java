package JAVA.LeetCode;

import java.util.*;

public class 找到最终的安全状态 {
    // graph[i]表示节点i可以到达的节点
    static boolean isSafe(int[][] graph,int start,boolean[] visited){
        visited[start]=true;
        if(graph[start].length==0) return true;
        for(int i:graph[start]){
            if(!visited[i]){
                if(!isSafe(graph,i,visited)) return false;
            }
            else if(visited[i]==true) return false;
        }
        return true;
    }
    static List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res=new ArrayList<>();
        boolean[] visited=new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            if(isSafe(graph,i,visited)) res.add(i);
        }
        return res;
    }
    public static void main(String[] args) {
        
    }
}
