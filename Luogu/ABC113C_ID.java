package Luogu;

import java.util.*;

public class ABC113C_ID {
    private static class Country{
        final int Pi,Yi;
        Country(int Pi,int Yi){
            this.Pi=Pi;
            this.Yi=Yi;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int M=sc.nextInt(),N=sc.nextInt();
        List<Country> ctry=new LinkedList<>();
        Map<Integer,List<Integer>> sorted=new HashMap<>();
        while(N-->0){
            int Pi=sc.nextInt(),Yi=sc.nextInt();
            ctry.add(new Country(Pi,Yi));
            if(sorted.containsKey(Pi)){
                sorted.get(Pi).add(Yi);
            }else{
                List<Integer> tmp=new ArrayList<>();
                tmp.add(Yi);
                sorted.put(Pi,tmp);
            }
        }
        sorted.forEach((I,L)->{
            L.sort(null);
        });
        for(Country ct:ctry){
            int pi=ct.Pi,yi=ct.Yi;
            int index=sorted.get(pi).indexOf(yi);
            System.out.println(String.format("%06d%06d",pi,index+1));
        }
        sc.close();
        System.out.println(M);
    }
}
