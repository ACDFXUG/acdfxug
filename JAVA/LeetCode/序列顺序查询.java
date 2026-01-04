package Java.LeetCode;

import java.util.*;

public class 序列顺序查询 {
    private static record Scenery(String name,int score)
    implements Comparable<Scenery>{
        public int compareTo(Scenery sc){
            return score==sc.score?
                name.compareTo(sc.name):sc.score-score;
        }
        public int hashCode(){
            return name.hashCode();
        }
        public boolean equals(Object sc){
            if(sc==this) return true;
            if(sc==null) return false;
            return sc instanceof Scenery s
                &&name.equals(s.name)&&score==s.score;
        }
    }
    private static class SORTracker{
        int browseTimes;
        final TreeSet<Scenery> scenes;
        SORTracker(){
            this.browseTimes=0;
            this.scenes=new TreeSet<>();
        }
        void add(String name, int score) {
            scenes.add(new Scenery(name,score));
        }
        String get() {
            final int eleCnt=scenes.size();
            if(browseTimes>=(eleCnt>>1)) {
                var de=scenes.descendingIterator();
                for(int i=eleCnt-1;i>browseTimes;i--){
                    if(de.hasNext()){
                        de.next();
                    }
                }
                ++browseTimes;
                return de.next().name();
            }else{
                var it=scenes.iterator();
                for(int i=0;i<browseTimes;i++){
                    if(it.hasNext()){
                        it.next();
                    }
                }
                ++browseTimes;
                return it.next().name();
            }
        }
    }
    public static void main(String[] args) {
        SORTracker sor=new SORTracker();
        sor.add("bradford",2);
        sor.add("branford",3);
        System.out.println(sor.get());
        sor.add("alps",2);
        System.out.println(sor.get());
        sor.add("orland",2);
        System.out.println(sor.get());
        sor.add("orlando",3);
        System.out.println(sor.get());
        sor.add("alpine",2);
        System.out.println(sor.get());
        System.out.println(sor.get());
    }
}
