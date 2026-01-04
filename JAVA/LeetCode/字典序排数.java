package Java.LeetCode;

import java.util.*;
import java.util.stream.IntStream;

public class 字典序排数 {
    static List<Integer> lexicalOrder(int n) {
        // List<Integer> result = new ArrayList<>();
        // for (int i = 1; i <= n; i++) {
        //     result.add(i);
        // }
        // result.sort((a, b) -> {
        //     String sa = String.valueOf(a);
        //     String sb = String.valueOf(b);
        //     return sa.compareTo(sb);
        // });
        
        // return result;
        return IntStream.rangeClosed(1,n).boxed()
        .sorted((I1,I2)->{
            String s1 = String.valueOf(I1);
            String s2 = String.valueOf(I2);
            return s1.compareTo(s2);
        }).toList();
    }
    public static void main(String[] args) {
        
    }
}
