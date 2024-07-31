package Luogu;

import java.util.*;
import java.util.Map.Entry;

public class 成绩排序 {
    private static
    record GradeScore(int C,int M,int E)
    implements Comparable<GradeScore> {
        int sum(){
            return C+M+E;
        }
        public int compareTo(GradeScore gs) {
            if(sum()!=gs.sum()){
                return gs.sum()-sum();
            }else if(C+M!=gs.C+gs.M){
                return gs.C+gs.M-C-M;
            }else if(Math.max(C,M)!=Math.max(gs.C,gs.M)){
                return Math.max(gs.C,gs.M)-Math.max(C,M);
            }
            return 0;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        Map<Integer,GradeScore> stu=new HashMap<>();
        for(int i=0;i<N;i++){
            int C=sc.nextInt();
            int M=sc.nextInt();
            int E=sc.nextInt();
            stu.put(i,new GradeScore(C,M,E));
        }
        List<GradeScore> rank=
        stu.entrySet().stream()
        .sorted((E1,E2)->E1.getValue().compareTo(E2.getValue()))
        .map(Entry::getValue)
        .toList();
        int r=0;
        List<Integer> rankIndex=new ArrayList<>();
        for(int i=0;i<rank.size();i++){
            if(i==0||rank.get(i).compareTo(rank.get(i-1))!=0){
                r=i+1;  //important!
            }
            rankIndex.add(r);
        }
        for(int i=0;i<N;i++){
            GradeScore temp=stu.get(i);
            int index=rank.indexOf(temp);
            System.out.println(rankIndex.get(index));
        }
        sc.close();
    }
}
