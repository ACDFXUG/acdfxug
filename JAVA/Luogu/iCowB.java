package JAVA.Luogu;

import java.util.*;

public class iCowB {
    private static class Song
    implements Comparable<Song>{
        int id,weight;
        Song(int id,int weight){
            this.id=id;
            this.weight=weight;
        }
        public int compareTo(Song s){
            return weight==s.weight?id-s.id:s.weight-weight;
        }
        public int hashCode(){
            return Objects.hash(id,weight);
        }
        public boolean equals(Object song){
            if(song==this) return true;
            if(song==null) return false;
            return song instanceof Song s
                &&id==s.id&&weight==s.weight;
        }
    }
    static Song[] playList;
    static Song currentPlay(TreeSet<Song> player,int N){
        Song cur=player.pollFirst();
        int avg=cur.weight/(N-1),remain=cur.weight%(N-1);
        cur.weight=0;
        for(var it=player.iterator();it.hasNext();){
            it.next().weight+=avg;
        }
        for(int i=1;remain>0;++i){
            if(i!=cur.id){
                ++playList[i].weight;
                --remain;
            }
        }
        // 清空player重新插入才行,直接add(pollFirst())错误
        player.clear();
        for(int i=1;i<=N;++i) player.add(playList[i]);
        return cur;
    }
    public static void main(String[] args) {
        TreeSet<Song> player=new TreeSet<>();
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),T=sc.nextInt();
        playList=new Song[N+1];
        for(int i=1,w;i<=N;++i){
            w=sc.nextInt();
            Song song=new Song(i,w);
            player.add(song);
            playList[i]=song;
        }
        for(int i=1;i<=T;++i){
            Song cur=currentPlay(player,N);
            System.out.println(cur.id);
        }
        sc.close();
    }
}
