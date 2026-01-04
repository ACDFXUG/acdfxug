package Java.LeetCode;

import java.util.*;

public class ディスカバリーチ {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        var index=new HashMap<Integer,Integer>(){{
            for(int i=1;i<=N;i++){
                put(sc.nextInt(),i);
            }
        }};
        for(int i=1;i<=N;i++){
            System.out.println(
                switch(index.get(i)){
                    case 1->"100000";
                    case 2->"50000";
                    case 3->"30000";
                    case 4->"20000";
                    case 5->"10000";
                    default->"0";
                }
            );
        }
        sc.close();
    }
}
