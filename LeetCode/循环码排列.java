package LeetCode;

import java.util.*;

public class 循环码排列 {
    static List<Integer> circularPermutation(int n,int start) {
        List<Integer> res=new ArrayList<>(1<<n);
        for(int i=0;i<1<<n;i++){
            res.add(i^(i>>1)^start);
        }
        return res;
    }
    public static void main(String[] args) {
        int n=3,start=2;
        var res=circularPermutation(n,start);
        final var fmt=String.format("dec:%%d, bin:%%0%dd\n",n);
        res.forEach(I->{
            System.out.printf(
                fmt,I,Integer.valueOf(
                    Integer.toString(I,2)
                )
            );
        });
    }
}
