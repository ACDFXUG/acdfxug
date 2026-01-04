package Java.LeetCode;

import java.util.*;

public class 跳跃游戏III {
    static boolean canReach(int[] arr, int start) {
        Queue<Integer> bfs=new ArrayDeque<>();
        Set<Integer> visited=new HashSet<>();
        bfs.add(start);
        visited.add(start);
        while(!bfs.isEmpty()){
            int curIdx=bfs.poll();
            if(arr[curIdx]==0) return true;
            else{
                int nxtL=curIdx-arr[curIdx],nxtR=curIdx+arr[curIdx];
                if(nxtL>=0&&!visited.contains(nxtL)){
                    bfs.add(nxtL);
                    visited.add(nxtL);
                }
                if(nxtR<arr.length&&!visited.contains(nxtR)){
                    bfs.add(nxtR);
                    visited.add(nxtR);
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] arr={3,0,2,1,2};
        System.out.println(canReach(arr,2));
    }
}
