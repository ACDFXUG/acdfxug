package JAVA.Luogu;

import java.util.*;
import java.util.stream.*;

public class POI {
    private static class Contestant{
        short index,solved;
        List<Integer> problems;
        Contestant(short index,short solved,List<Integer> problems){
            this.index=index;
            this.solved=solved;
            this.problems=problems;
        }
        public boolean equals(Object con){
            if(con==this) return true;
            if(con==null) return false;
            return con instanceof Contestant c
                &&index==c.index
                &&solved==c.solved
                &&problems.equals(c.problems);
        }
        public int hashCode(){
            return Objects.hash(index,solved,problems);
        }
    }
    public static void main(String[] args) {
        var sc=new Scanner(System.in);
        int N=sc.nextInt(),T=sc.nextInt(),
            P=sc.nextInt(),index[]={1};
        var cons=new Contestant[N+1];
        int[] scores=new int[T+1];
        for(int i=1;i<=N;i++){
            short solved=0;
            List<Integer> problems=new ArrayList<>();
            for(int j=1;j<=T;j++){
                int score=sc.nextInt();
                if(score>0){
                    solved++;
                    problems.add(j);
                }else{
                    scores[j]++;
                }
            }
            cons[i]=new Contestant((short)i,solved,problems);
        }
        var indexMap=Arrays.stream(cons)
            .filter(Objects::nonNull)
            .collect(Collectors.toMap(C->C,C->{
                int score=0;
                for(int p:C.problems){
                    score+=scores[p];
                }
                return score;
            }));
        var indexed=indexMap.entrySet().stream()
            .sorted((E1,E2)->{
                if(E1.getValue()!=E2.getValue()){
                    return E2.getValue()-E1.getValue();
                }else{
                    if(E1.getKey().solved!=E2.getKey().solved){
                        return E2.getKey().solved-E1.getKey().solved;
                    }else{
                        return E1.getKey().index-E2.getKey().index;
                    }
                }
            }).collect(Collectors.toMap(Map.Entry::getKey,$->index[0]++));
        System.out.println(indexMap.get(cons[P])+" "+indexed.get(cons[P]));
        sc.close();
    }
}
