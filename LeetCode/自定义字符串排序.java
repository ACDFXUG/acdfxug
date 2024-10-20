package LeetCode;

import java.util.*;

public class 自定义字符串排序 {
    static String customSortString(String order, String s) {
        List<Character> charArray=new ArrayList<>(s.length()){{
            for(char c:s.toCharArray()){
                add(c);
            }
        }};
        charArray.sort((C1,C2)->
        order.indexOf(C1)-order.indexOf(C2));
        StringBuilder ans=new StringBuilder();
        for(char c:charArray){
            ans.append(c);
        }
        return ans.toString();
    }
    public static void main(String[] args) {
        String order="cbafg",s="abcd";
        System.out.println(customSortString(order,s));
    }
}
