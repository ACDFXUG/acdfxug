package Java.LeetCode;

import java.util.*;

public class 坏了的计算器 {
    private static record State(long val,int step){
        public int hashCode(){
            return Objects.hash(val,step);
        }
        public boolean equals(Object state){ 
            if(this==state) return true;
            if(state==null) return false;
            return state instanceof State s
                &&val==s.val 
                &&step==s.step;
        }
    }
    static int brokenCalc(int startValue, int target) {
        State init=new State(startValue,0);
        Queue<State> bfs=new ArrayDeque<>();
        Set<State> visited=new HashSet<>();
        bfs.offer(init);
        visited.add(init);
        while(!bfs.isEmpty()){
            var s=bfs.poll();
            if(s.val==target) return s.step;
            if(s.val<target){
                bfs.offer(new State(s.val*2,s.step+1));
                bfs.offer(new State(s.val-1,s.step+1));
            }else{
                bfs.offer(new State(s.val-1,s.step+1));
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        System.out.println(brokenCalc(5000,10000000));
    }
}
