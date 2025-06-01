package JAVA.Luogu;

import java.util.*;

public class くつがくっつく {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int L=sc.nextInt(),R=sc.nextInt();
        int shoe=0;
        Map<Integer,int[]> shoes=new HashMap<>();
        for(int i=0;i<L;i++){
            int size=sc.nextInt();
            if(shoes.containsKey(size)){
                shoes.get(size)[0]++;
            }else{
                shoes.put(size,new int[]{1,0});
            }
        }
        for(int i=0;i<R;i++){
            int size=sc.nextInt();
            if(shoes.containsKey(size)){
                shoes.get(size)[1]++;
            }else{
                shoes.put(size,new int[]{0,1});
            }
        }
        for(Map.Entry<Integer,int[]> E:shoes.entrySet()){
            shoe+=Math.min(E.getValue()[0],E.getValue()[1]);
        }
        System.out.println(shoe);
        sc.close();
    }
}
