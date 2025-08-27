package JAVA.LeetCode;

import java.util.*;

public class 到达终点数字 {
    private static record State(int step,int coord,boolean posi){
        public int hashCode(){
            return Objects.hash(step,coord,posi);
        }
        public boolean equals(Object obj){ 
            if(obj==this) return true;
            if(obj==null) return false;
            return obj instanceof State s
                &&step==s.step
                &&coord==s.coord
                &&posi==s.posi;
        }
        @Override
        public String toString() {
            return "["+step+" "+coord+" "+posi+"]";
        }
    }
    static int reachNumber(int target) {
        Set<State> visited=new HashSet<>();
        Queue<State> bfs=new ArrayDeque<>();
        State left=new State(1,0,false);
        State right=new State(1,0,true);
        bfs.add(left);
        bfs.add(right);
        while(!bfs.isEmpty()){ 
            var curState=bfs.poll();
            System.err.println(curState);
            if(curState.coord==target) return curState.step-1;
            if(curState.posi){
                var nxt1=new State(curState.step+1,curState.coord+curState.step,false);
                if(!visited.contains(nxt1)){
                    bfs.add(nxt1);
                    visited.add(nxt1);
                }
                var nxt2=new State(curState.step+1,curState.coord+curState.step,true);
                if(!visited.contains(nxt2)){
                    bfs.add(nxt2);
                    visited.add(nxt2);
                }
            }else{
                var nxt1=new State(curState.step+1,curState.coord-curState.step,false);
                if(!visited.contains(nxt1)){
                    bfs.add(nxt1);
                    visited.add(nxt1);
                }
                var nxt2=new State(curState.step+1,curState.coord-curState.step,true);
                if(!visited.contains(nxt2)){
                    bfs.add(nxt2);
                    visited.add(nxt2);
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        System.out.println(reachNumber(1000000));
    }
}
