package Java.LeetCode;

import java.util.*;
import java.util.regex.*;

public class 价格减免 {
    static final Pattern PRICE=Pattern.compile("\\$(\\d+)");
    static String discountPrices(String sentence, int discount) {
        String[] words=sentence.split("\\s");
        StringJoiner discnt=new StringJoiner("\s");
        for(String word:words){
            Matcher prc=PRICE.matcher(word);
            if(prc.matches()){
                double price=Double.parseDouble(prc.group(1));
                discnt.add(String.format("$%.2f",price*(100-discount)/100));
            }else{
                discnt.add(word);
            }
        }
        return discnt.toString();
    }
    public static void main(String[] args) {
        String sentence="there are $1 $2 and 5$ candies in the shop";
        System.out.println(discountPrices(sentence, 50));
    }
}
