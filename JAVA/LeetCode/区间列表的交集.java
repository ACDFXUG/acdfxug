package JAVA.LeetCode;

import java.util.*;

public class 区间列表的交集 {
    private static class Interval
    implements Comparable<Interval>{
        final int s,e;
        Interval(int s,int e){
            this.s=s;
            this.e=e;
        }
        Interval(int[] pair){
            this(pair[0],pair[1]);
        }
        public int compareTo(Interval itv) {
            return s==itv.s?e-itv.e:s-itv.s;
        }
        public int hashCode(){
            return Objects.hash(s,e);
        }
        public boolean equals(Object inter){
            if(this==inter) return true;
            if(inter==null) return false;
            return inter instanceof Interval itv
                &&s==itv.s&&e==itv.e;
        }
        boolean hasIntersection(Interval itv){
            final int max=Math.max(s,itv.s);
            final int min=Math.min(e,itv.e);
            return max<=min;
        }
        Interval intersect(Interval itv){
            if(hasIntersection(itv)){
                return new Interval(
                    Math.max(s,itv.s),
                    Math.min(e,itv.e)
                );
            }else{
                return new Interval(-1,-1);
            }
        }
        int[] toArray(){
            return s>=0&&e>=0?new int[]{s,e}:new int[0];
        }
        public String toString(){
            return "["+s+", "+e+"]";
        }
    }
    static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if(firstList.length==0||secondList.length==0) return new int[0][0];
        else{
            var ans=new ArrayList<Interval>();
            for(int i=0;i<firstList.length;i++){
                for(int j=0;j<secondList.length;j++){
                    var up=new Interval(firstList[i]);
                    var down=new Interval(secondList[j]);
                    if(up.hasIntersection(down)){
                        var inter=up.intersect(down);
                        ans.add(inter);
                    }
                }
            }
            return ans.stream()
                .map(Interval::toArray)
                .toArray(int[][]::new);
        }
    }
    public static void main(String[] args) {
        int[][] first={{0,2},{5,10},{13,23},{24,25}};
        int[][] second={{1,5},{8,12},{15,24},{25,26}};
        System.out.println(Arrays.deepToString(intervalIntersection(first, second)));
    }
}
