package JAVA.LeetCode;

import java.util.*;

public class 钥匙和房间 {
    static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Queue<Integer> bfs=new ArrayDeque<>();
        Set<Integer> visited=new HashSet<>();
        bfs.add(0);
        visited.add(0);
        while(!bfs.isEmpty()){
            var curRoom=bfs.poll();
            rooms.get(curRoom).forEach(room->{
                if(!visited.contains(room)){
                    bfs.add(room);
                    visited.add(room);
                }
            });
        }
        return visited.size()==rooms.size();
    }
    public static void main(String[] args) {
        List<List<Integer>> rooms=new ArrayList<>();
        rooms.add(List.of(1,3));
        rooms.add(List.of(3,0,1));
        rooms.add(List.of(2));
        rooms.add(List.of(0));
        System.out.println(canVisitAllRooms(rooms));
    }
}
