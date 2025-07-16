package JAVA.LeetCode;

import java.util.*;

public class 到家的最少跳跃次数 {
    private static class State{
        int current;
        boolean backward;
        int step;
        State(int cur,boolean bkwd,int stp){
            this.current=cur;
            this.backward=bkwd;
            this.step=stp;
        }
        public int hashCode(){
            return Objects.hash(current,backward);
        }
        public boolean equals(Object stt){ 
            if(stt==this) return true;
            if(stt==null) return false;
            return stt instanceof State s
                &&current==s.current
                &&backward==s.backward;
        }
        public String toString(){
            return String.format("{current: %d, backward: %b, step: %d}",current,backward,step);
        }
    }
    static int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> fbdSet=new HashSet<>(){{
            for(int i:forbidden) add(i);
        }};
        Queue<State> bfs=new ArrayDeque<>();
        Set<State> visited=new HashSet<>();
        State init=new State(0,false,0);
        visited.add(init);
        bfs.add(init);
        while(!bfs.isEmpty()){
            System.out.println(bfs);
            var curState=bfs.poll();
            if(curState.current==x) return curState.step;
            int fwd=curState.current+a;
            var tmp1=new State(fwd,false,curState.step+1);
            if(fwd<=100000&&!fbdSet.contains(fwd)&&!visited.contains(tmp1)){
                bfs.add(tmp1);
                visited.add(tmp1);
            }
            if(!curState.backward){
                int bwd=curState.current-b;
                var tmp2=new State(bwd,true,curState.step+1);
                if(bwd>=0&&!fbdSet.contains(bwd)&&!visited.contains(tmp2)){
                    bfs.add(tmp2);
                    visited.add(tmp2);
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] forbidden={162,118,178,152,167,100,40,74,199,186,26,73,200,127,30,124,193,84,184,36,103,149,153,9,54,154,133,95,45,198,79,157,64,122,59,71,48,177,82,35,14,176,16,108,111,6,168,31,134,164,136,72,98};
        System.out.println(minimumJumps(forbidden,29,98,80));
    }
}
