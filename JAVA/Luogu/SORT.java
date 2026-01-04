package Java.Luogu;

import java.util.*;

public class SORT {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),C=sc.nextInt();
        Map<Integer,int[]> locate=new HashMap<>();
        for(int i=0;i<N;i++){
            int ai=sc.nextInt(),first;
            if(!locate.containsKey(ai)){
                locate.put(ai,new int[]{first=i,1});
                System.out.println(first);
            }else{
                locate.get(ai)[1]++;
            }
        }
        locate.entrySet().stream()
        .sorted((E1,E2)->{
            int[] lct=E1.getValue(),
            lct1=E2.getValue();
            return lct[1]!=lct1[1]?
            lct1[1]-lct[1]:lct[0]-lct1[0];
        }).forEach(E->{
            int freq=E.getValue()[1],ai=E.getKey();
            for(int i=freq;i-->0;){
                System.out.print(ai+" ");
            }
        });
        System.out.println(C);
        sc.close();
    }
}
