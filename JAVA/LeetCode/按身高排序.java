package JAVA.LeetCode;

import java.util.*;

public class 按身高排序 {
    static String[] sortPeople(String[] names, int[] heights) {
        TreeMap<Integer,String> people=
        new TreeMap<Integer,String>((H1,H2)->H2-H1){{
            for(int i=0;i<heights.length;i++){
                put(heights[i],names[i]);
            }
        }};
        return people.entrySet().stream()
        .map(E->E.getValue())
        .toArray(String[]::new);
    }
    public static void main(String[] args) {
        String[] names=new String[]{"Alice","Bob","Bob"};
        int[] heights=new int[]{155,185,150};
        System.out.println(Arrays.toString(sortPeople(names, heights)));
    }
}
