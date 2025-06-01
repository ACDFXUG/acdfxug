package JAVA.Luogu;

import java.util.*;

public class iCowB {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),T=sc.nextInt();
        Map<Integer,Integer> song=new HashMap<>();
        int[] ans=new int[T];
        for(int i=1;i<=N;i++){
            int Ri=sc.nextInt();
            song.put(i,Ri);
        }
        sc.close();
        for(int s=0;s<T;s++){
            int index=0x7fffffff,value=-1;
            for(Map.Entry<Integer,Integer> entry:song.entrySet()){
                value=entry.getValue()>value?entry.getValue():value;
            }
            for(Map.Entry<Integer,Integer> entry:song.entrySet()){
                if(entry.getValue()==value){
                    index=entry.getKey()<index?entry.getKey():index;
                }
            }
            ans[s]=index;
            int Ri=song.get(index),r=Ri%(N-1),n=Ri/(N-1);
            for(int i=1;i<=N;i++){
                if(i!=index){
                    song.put(i,song.get(i)+n);
                }else{
                    song.put(i,0);
                }
            }
            if(r!=0){
                for(int i=1;i<=r;){
                    if(i!=index){
                        song.put(i,song.get(i++)+1);
                    }else{
                        continue;
                    }
                }
            }
        }
        for(int x:ans){
            System.out.println(x);
        }
    }
}
