package JAVA.Luogu;

import java.util.*;
import java.util.stream.*;

public class Roulette {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        Map<Integer,HashSet<Integer>> guess=new HashMap<>(N);
        for(int i=1;i<=N;i++){
            int C=sc.nextInt();
            var tmp=new HashSet<Integer>(C){{
                for(int j=0;j<C;j++){
                    add(sc.nextInt());
                }
            }};
            guess.put(i,tmp);
        }
        int X=sc.nextInt();
        sc.close();
        var ans=guess.entrySet().stream()
            .filter(e->e.getValue().contains(X))
            .collect(Collectors.toList());
        if(ans.isEmpty()){
            System.out.println("0");
            return;
        }
        ans.sort((E1,E2)->{
            int c1=E1.getValue().size(),
                c2=E2.getValue().size();
            return c1==c2?E1.getKey()-E2.getKey():c1-c2;
        });
        int minC=ans.get(0).getValue().size(),size=0;
        StringJoiner joiner=new StringJoiner(" ");
        for(var e:ans){
            if(e.getValue().size()==minC){ 
                joiner.add(e.getKey().toString());
                size++;
            }
        }
        System.out.println(size);
        System.out.println(joiner);
    }
}
