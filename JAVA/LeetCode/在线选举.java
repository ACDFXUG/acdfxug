package JAVA.LeetCode;

import java.util.*;

public class 在线选举 {
    private static class TopVotedCandidate {
        TreeMap<Integer,Integer> winner;
        HashMap<Integer,Integer> voteCount;
        TopVotedCandidate(int[] persons, int[] times) {
            this.winner=new TreeMap<>();
            this.voteCount=new HashMap<>();
            int curMax=0;
            for(int i=0;i<persons.length;++i){
                voteCount.merge(persons[i],1,Integer::sum);
                if(voteCount.get(persons[i])>=curMax){
                    curMax=voteCount.get(persons[i]);
                    winner.put(times[i],persons[i]);
                }
            }
        }
        int q(int t) {
            return winner.floorEntry(t).getValue();
        }
    }
    public static void main(String[] args) {
        TopVotedCandidate tvc=new TopVotedCandidate(new int[]{0,1,1,0,0,1,0},new int[]{0,5,10,15,20,25,30});
        System.out.println(tvc.q(3));
        System.out.println(tvc.q(12));
        System.out.println(tvc.q(25));
        System.out.println(tvc.q(15));
        System.out.println(tvc.q(24));
        System.out.println(tvc.q(8));
    }
}
