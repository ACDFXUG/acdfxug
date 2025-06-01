package JAVA.Luogu;

import java.util.*;

public class AtColor {
    private static record Interval(int left,int right)
    implements Comparable<Interval>{
        public int hashCode(){
            return Objects.hash(left,right);
        }
        public boolean equals(Object itv){
            if(this==itv){
                return true;
            }
            if(itv==null){
                return false;
            }
            return itv instanceof Interval interval&&
            left==interval.left&&right==interval.right;
        }
        public int compareTo(Interval itv){
            return left-itv.left;
        }
        boolean isIntersect(Interval itv){
            int min=Math.max(left,itv.left);
            int max=Math.min(right,itv.right);
            return min<=max;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        var itvs=new ArrayList<Interval>(N){{
            for(int i=0;i<N;i++){
                add(new Interval(
                    sc.nextInt(),
                    sc.nextInt()
                ));
            }
        }};
        itvs.sort(null);
        var times=new LinkedHashMap<Interval,Integer>(){{
            put(itvs.get(0),1);
        }};
        for(int i=1;i<N;i++){
            var itv=itvs.get(i);
            var last=times.lastEntry().getKey();
            times.merge(itv.isIntersect(last)?last:itv,1,(a,b)->a+b);
        }
        System.out.println(Collections.max(times.values()));
        sc.close();
    }
}
