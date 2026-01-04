package Java.LeetCode;

import java.util.*;

public class 二进制矩阵中的最短路径 {
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
    }
    static final List<Pair<Integer,Integer>> dirs=List.of(
        new Pair<>(0,1),new Pair<>(1,0),
        new Pair<>(0,-1),new Pair<>(-1,0),
        new Pair<>(1,1),new Pair<>(1,-1),
        new Pair<>(-1,1),new Pair<>(-1,-1)
    );
    static boolean isValid(Pair<Integer,Integer> nxt,int[][] grid){
        return nxt.x>=0&&nxt.x<grid.length
            &&nxt.y>=0&&nxt.y<grid[0].length
            &&grid[nxt.x][nxt.y]==0;
    }
    static int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0]==1) return -1;
        Set<Pair<Integer,Integer>> visited=new HashSet<>();
        Queue<Pair<Pair<Integer,Integer>,Integer>> bfs=
            new ArrayDeque<>();
        final Pair<Integer,Integer> start=new Pair<>(0,0);
        final Pair<Integer,Integer> end=new Pair<>(grid.length-1,grid[0].length-1);
        bfs.offer(new Pair<>(start,1));
        visited.add(start);
        while(!bfs.isEmpty()){
            var cur=bfs.poll();
            var pos=cur.x;
            var step=cur.y;
            if(pos.equals(end)) return step;
            dirs.forEach(dir->{
                var nxt=new Pair<>(pos.x+dir.x,pos.y+dir.y);
                if(isValid(nxt,grid)&&!visited.contains(nxt)){
                    bfs.offer(new Pair<>(nxt,step+1));
                    visited.add(nxt);
                }
            });
        }
        return -1;
    }
    public static void main(String[] args) {
        int[][] grid={
            {0,0,0},
            {1,1,0},
            {1,1,1}
        };
        System.out.println(shortestPathBinaryMatrix(grid));
    }
}
