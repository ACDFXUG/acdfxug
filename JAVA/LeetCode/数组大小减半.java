package JAVA.LeetCode;

import java.util.*;
import java.util.Map.Entry;

public class 数组大小减半 {
    static int minSetSize(int[] arr) {
        var count=new HashMap<Integer,Integer>(){{
            for(int x:arr){
                put(x,getOrDefault(x,0)+1);
            }
        }};
        var ranked=new TreeSet<Entry<Integer,Integer>>(  //必须严格有序
            (E1,E2)->{
                int v1=E1.getValue(),
                    v2=E2.getValue();
                return v2==v1?
                E1.getKey()-E2.getKey():v2-v1;
            }
        );
        count.entrySet()
        .forEach(ranked::add);
        int l$2=arr.length>>1,
        suml=0,ans=0;
        for(var E:ranked){
            suml+=E.getValue();
            ans++; 
            if(suml>=l$2){
                return ans;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] arr={1,1,2,2,3,3,4,4};
        System.out.println(minSetSize(arr));
        
    }
}
