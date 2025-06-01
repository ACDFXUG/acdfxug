package JAVA.LeetCode;

import java.util.*;
import static java.util.Map.Entry.*;

public class 不同整数的最少数目 {
    static int findLeastNumOfUniqueInts(int[] arr, int k) {
        var count=new HashMap<Integer,Integer>(){{
            for(int i:arr){
                put(i,getOrDefault(i,0)+1);
            }
        }};
        var cnt=count.entrySet().stream()
            .sorted(comparingByValue())
            .toList();
        int L=cnt.size();
        for(int i=0,l=L;i<l;i++){
            int kk=cnt.get(i).getValue();
            if(kk<=k){
                k-=kk;
                L--;
            }
        }
        return L;
    }
    public static void main(String[] args) {
        int[] arr={4,3,1,1,4,3,2};
        System.out.println(findLeastNumOfUniqueInts(arr,3));
    }
}
