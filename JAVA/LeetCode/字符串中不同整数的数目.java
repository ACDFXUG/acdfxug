package JAVA.LeetCode;

import java.util.*;
import java.util.regex.*;

public class 字符串中不同整数的数目 {
    static final Pattern NUMBER=Pattern.compile("\\d+");
    static int numDifferentIntegers(String word) {
        Set<String> dup=new HashSet<>();
        for(Matcher num=NUMBER.matcher(word);num.find();){
            dup.add(num.group().replaceFirst("^0+",""));
        }
        return dup.size();
    }
    public static void main(String[] args) {
        System.out.println(
            numDifferentIntegers("a1b01c001")
        );
    }
}
