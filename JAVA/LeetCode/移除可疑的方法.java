package Java.LeetCode;

import java.util.*;
import java.util.stream.IntStream;

public class 移除可疑的方法 {
    static List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        Map<Integer,Set<Integer>> subMethods=new HashMap<>();
        Map<Integer,Set<Integer>> parentMthd=new HashMap<>();
        for(int[] inv:invocations){
            subMethods.computeIfAbsent(inv[0],_->new HashSet<>()).add(inv[1]);
            parentMthd.computeIfAbsent(inv[1],_->new HashSet<>()).add(inv[0]);
        }
        Set<Integer> bugMethod=new HashSet<>();
        Queue<Integer> bfs=new ArrayDeque<>();
        bfs.add(k);
        bugMethod.add(k);
        while(!bfs.isEmpty()){
            var cur=bfs.poll();
            for(var sub:subMethods.getOrDefault(cur,new HashSet<>())){
                if(!bugMethod.contains(sub)){
                    bugMethod.add(sub);
                    bfs.add(sub);
                }
            }
        }
        for(var bug:bugMethod){
            for(var parent:parentMthd.getOrDefault(bug,new HashSet<>())){
                if(!bugMethod.contains(parent)){
                    return IntStream.range(0,n)
                        .boxed().toList();
                }
            }
        }
        return IntStream.range(0,n)
            .filter(i->!bugMethod.contains(i))
            .boxed().toList();
    }
    public static void main(String[] args) {
        int[][] inv={
            {1,2},{0,1},{2,0}
        };
        System.out.println(remainingMethods(3,2,inv));
    }
}
