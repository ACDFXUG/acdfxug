package JAVA.LeetCode;

import java.util.*;

public class 找到连续赢K场比赛的第一位玩家 {
    static int findWinningPlayer(int[] skills, int k) {
        Deque<Integer> player=new ArrayDeque<>(skills.length);
        int maxSkill=-1;
        for(int i=0;i<skills.length;++i){
            maxSkill=Math.max(maxSkill,skills[i]);
            player.add(i);
        }
        Deque<Integer> winner=new ArrayDeque<>();
        for(;winner.size()<k;){
            int p1=player.poll();
            int p2=player.poll();
            if(skills[p1]==maxSkill){
                return p1;
            }else if(skills[p2]==maxSkill){
                return p2;
            }
            if(skills[p1]>skills[p2]){
                player.addFirst(p1);
                player.addLast(p2);
                if(winner.isEmpty()){
                    winner.push(p1);
                }else if(winner.peek()!=p1){
                    winner.clear();
                    winner.push(p1);
                }else{
                    winner.push(p1);
                }
            }else if(skills[p1]<skills[p2]){
                player.addFirst(p2);
                player.addLast(p1);
                if(winner.isEmpty()){
                    winner.push(p2);
                }else if(winner.peek()!=p2){
                    winner.clear();
                    winner.push(p2);
                }else{
                    winner.push(p2);
                }
            }
        }
        return winner.peek();
    }
    public static void main(String[] args) {
        int[] skills={16,4,7,17};
        System.out.println(findWinningPlayer(skills,562084119));
    }
}
