package JAVA.Luogu;

import java.util.*;

public class 瑞士轮 {
    private static class Campaigner
    implements Comparable<Campaigner>{
        int score,id,ability;
        Campaigner(int id,int score){
            this.id=id;
            this.score=score;
        }
        void setAbility(int ability){
            this.ability=ability;
        }
        void increaseScore(){
            ++score;
        }
        public int compareTo(Campaigner com){
            return score==com.score?id-com.id:com.score-score;
        }
        public boolean equals(Object obj){
            if(obj==this) return true;
            if(obj==null) return false;
            return obj instanceof Campaigner com
                &&com.id==id&&com.score==score;
        }
        public String toString() {
            return "{score=" + score + ", id=" + id + ", ability=" + ability + "}";
        }
    }
    static Deque<Campaigner> merge(Deque<Campaigner> deque1,Deque<Campaigner> deque2){
        Deque<Campaigner> ans=new ArrayDeque<>();
        while(!deque1.isEmpty()&&!deque2.isEmpty()){
            if(deque1.peek().compareTo(deque2.peek())<=0){
                ans.add(deque1.poll());
            }else{
                ans.add(deque2.poll());
            }
        }
        while(!deque1.isEmpty()){
            ans.add(deque1.poll());
        }
        while(!deque2.isEmpty()){
            ans.add(deque2.poll());
        }
        return ans;
    }
    static List<Campaigner> campaign(List<Campaigner> campaigners,int rounds){
        Deque<Campaigner> deque=new ArrayDeque<>(campaigners);
        for(int r=0;r<rounds;r++){
            Deque<Campaigner> winners=new ArrayDeque<>();
            Deque<Campaigner> losers=new ArrayDeque<>();
            while(deque.size()>1){
                Campaigner com1=deque.poll();
                Campaigner com2=deque.poll();
                if(com1.ability>com2.ability){
                    com1.increaseScore();
                    winners.add(com1);
                    losers.add(com2);
                }else{
                    com2.increaseScore();
                    winners.add(com2);
                    losers.add(com1);
                }
            }
            deque=merge(winners,losers);
        }
        return new ArrayList<>(deque);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        final int N=sc.nextInt(),R=sc.nextInt(),
            Q=sc.nextInt(),L=N<<1;
        List<Campaigner> campaigners=new ArrayList<>(L);
        for(int i=1;i<=L;i++){
            int initScore=sc.nextInt();
            campaigners.add(new Campaigner(i,initScore));
        }
        for(int i=0;i<L;i++){
            campaigners.get(i).setAbility(sc.nextInt());
        }
        campaigners.sort(null);
        campaigners=campaign(campaigners,R);
        System.out.println(campaigners.get(Q-1).id);
        sc.close();
    }
}
