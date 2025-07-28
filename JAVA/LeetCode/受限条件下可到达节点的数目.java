package JAVA.LeetCode;

import java.util.*;

public class 受限条件下可到达节点的数目 {
    static int reachableNodes(int n, int[][] edges, int[] restricted) {
        Map<Integer,Set<Integer>> adj=new HashMap<>();
        Set<Integer> ban=new HashSet<>();
        for(var edge:edges){
            adj.computeIfAbsent(edge[0],val->new HashSet<>()).add(edge[1]);
            adj.computeIfAbsent(edge[1],val->new HashSet<>()).add(edge[0]);
        }
        for(var node:restricted){
            ban.add(node);
        }
        Queue<Integer> q=new ArrayDeque<>();
        int ans=0;
        q.offer(0);
        ban.add(0);
        while(!q.isEmpty()){
            var cur=q.poll();
            ++ans;
            for(var next:adj.getOrDefault(cur,new HashSet<>())){
                if(!ban.contains(next)){
                    q.offer(next);
                    ban.add(next);
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        
    }
}
