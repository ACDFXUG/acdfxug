package JAVA.LeetCode;

import java.util.*;

public class 格雷码 {
    static List<Integer> grayCode(int n){
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<(1<<n);i++){
            list.add(i^(i>>1));
        }
        return list;
    }
    public static void main(String[] args) {
        grayCode(2).forEach(i->System.out.print(i+" "));
    }
}
