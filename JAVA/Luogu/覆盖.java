package JAVA.Luogu;

import java.util.*;

public class 覆盖 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),M=sc.nextInt();
        int B=sc.nextInt(),G=sc.nextInt();
        int lattice=0;
        var notSwept=new HashSet<Integer>(){{
            for(int i=1;i<=N;i++){
                add(i);
            }
        }};
        Set<Integer> sweptCol=new HashSet<>();
        for(int i=1,x,y;i<=B;i++){
            x=sc.nextInt();
            y=sc.nextInt();
            for(int j=x;j<=y;j++){
                if(notSwept.contains(j)){
                    lattice+=M;
                    notSwept.remove(j);
                }
            }
        }
        for(int i=1,x,y;i<=G;i++){
            x=sc.nextInt();
            y=sc.nextInt();
            for(int j=x;j<=y;j++){
                sweptCol.add(j);
            }
        }
        lattice+=notSwept.size()*sweptCol.size();
        System.out.println(lattice);
        sc.close();
    }
}
