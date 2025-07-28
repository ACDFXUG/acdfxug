package JAVA.LeetCode;

import java.util.*;

public class 边积分最高的节点 {
    static int edgeScore(int[] edges) {
        Map<Integer,Long> sum=new HashMap<>();
        for(int i=0;i<edges.length;++i){
            sum.merge(edges[i],Long.valueOf(i),Long::sum);
        }
        System.out.println(sum);
        return sum.entrySet().stream()
            .min((E1,E2)->{
                long a=E1.getValue();
                long b=E2.getValue();
                return a==b?E1.getKey()-E2.getKey():(b>a?1:-1);
            }).get().getKey();
    }
    public static void main(String[] args) {
        System.out.println(edgeScore(new int[]{
            0,0,1,1,0
        }));
    }
}
