package JAVA.Luogu;

import java.util.*;

public class Hindeks {
    public static void main(String[] args) {
        try(Scanner sc=new Scanner(System.in)){
            int N=sc.nextInt();
            TreeMap<Integer,Integer> num_cnt=new TreeMap<>();
            for(int i=1;i<=N;i++){
                num_cnt.merge(sc.nextInt(),1,Integer::sum);
            }
            for(int H=N;H>=0;H--){
                if(num_cnt.tailMap(H).values().stream()
                .mapToInt(Integer::intValue)
                .sum()>=H){
                    System.out.println(H);
                    return;
                }
            }
            System.out.println(0);
        }
    }
}
