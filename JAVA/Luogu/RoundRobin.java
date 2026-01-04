package Java.Luogu;

import java.util.*;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        char[][] diff=new char[N][N];
        for(int i=0;i<N;i++){
            diff[i]=sc.next().toCharArray();
        }
        Map<Integer,Integer> wins=new HashMap<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                wins.merge(i+1,diff[i][j]=='o'?1:0,(a,b)->a+b);
            }
        }
        StringJoiner ans=new StringJoiner(" ");
        wins.entrySet().stream()
            .sorted((E1,E2)->{
                int e1=E1.getValue(),e2=E2.getValue();
                return e1==e2?E1.getKey()-E2.getKey():e2-e1;
            }).forEach(E->ans.add(E.getKey().toString()));
        System.out.println(ans);
        sc.close();
    }
}
