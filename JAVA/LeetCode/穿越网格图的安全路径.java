package JAVA.LeetCode;

import java.util.*;

public class 穿越网格图的安全路径 {
    private static record Pair<K,V>(K x,V y){
        public int hashCode(){
            return Objects.hash(x,y);
        }
        public boolean equals(Object pair){
            if(this==pair) return true;
            if(pair==null) return false;
            return pair instanceof Pair p
                &&x.equals(p.x)&&y.equals(p.y);
        }
        public String toString(){
            return "("+x+", "+y+")";
        }
    }
    static boolean isValid(int m,int n,Pair<Integer,Integer> pos){
        return pos.x>=0&&pos.x<m
            &&pos.y>=0&&pos.y<n;
    }
    static final int[][] dirs={
        {-1,0},{1,0},{0,-1},{0,1}
    };
    static boolean findSafeWalk(List<List<Integer>> grid, int health) {
        final int m=grid.size(),n=grid.get(0).size();
        Queue<Pair<Pair<Integer,Integer>,Integer>> bfs=new PriorityQueue<>((p1,p2)->p2.y-p1.y);
        Set<Pair<Integer,Integer>> visited=new LinkedHashSet<>();
        var start=new Pair<>(0,0);
        bfs.add(new Pair<>(start,health-grid.get(0).get(0)));
        visited.add(start);
        final var end=new Pair<>(m-1,n-1);
        while(!bfs.isEmpty()){
            var cur=bfs.poll();
            var pos=cur.x;
            var hlth=cur.y;
            if(pos.equals(end)&&hlth>0){
                System.out.println(visited);
                return true;
            }
            for(var dir:dirs){
                int x=pos.x+dir[0];
                int y=pos.y+dir[1];
                var nxt=new Pair<>(x,y);
                if(isValid(m,n,nxt)&&hlth-grid.get(x).get(y)>0&&!visited.contains(nxt)){
                    bfs.offer(new Pair<>(nxt,hlth-grid.get(x).get(y)));
                    visited.add(nxt);
                }
            }
        }
        System.out.println(visited);
        return false;
    }
    public static void main(String[] args) {
        var grid=new ArrayList<List<Integer>>(){{
            add(List.of(1,0,1,1));
            add(List.of(0,0,0,1));
            add(List.of(1,0,1,1));
            add(List.of(0,1,1,0));
            add(List.of(1,0,0,1));
        }};
        System.out.println(findSafeWalk(grid,4));
    }
}
