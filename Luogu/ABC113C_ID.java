package Luogu;

import java.util.*;

@SuppressWarnings("unused")
public class ABC113C_ID {
    private static record Country(int Pi,int Yi){}
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int M=sc.nextInt(),N=sc.nextInt();
        List<Country> cntry=new ArrayList<>(N);
        Map<Integer,TreeMap<Integer,Integer>> cntryMap=new HashMap<>(N);
        for(int i=0;i<N;i++){
            int Pi=sc.nextInt(),Yi=sc.nextInt();
            cntry.add(new Country(Pi,Yi));
            cntryMap.computeIfAbsent(Pi,$->new TreeMap<>()).put(Yi,0);
        }
        cntryMap.forEach((I,TREE)->{
            int i=1;
            for(var en:TREE.entrySet()){
                en.setValue(i++);
            }
        });
        cntry.forEach(cnt->{
            int pi=cnt.Pi,yi=cnt.Yi,index=cntryMap.get(pi).get(yi);
            System.out.printf("%06d%06d\n",pi,index);
        });
        sc.close();
    }
}
