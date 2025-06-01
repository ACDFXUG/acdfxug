package JAVA.LeetCode;

import java.util.*;

public class 搜索推荐系统 {
    static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<String> prd=Arrays.asList(products);
        List<List<String>> ans=new ArrayList<>();
        for(int k=1;k<=searchWord.length();k++){
            String sub=searchWord.substring(0,k);
            ans.add(prd.stream().parallel()
            .filter(S->S.startsWith(sub))
            .sorted(String::compareTo)
            .limit(3).toList());
        }
        return ans;
    }
    public static void main(String[] args) {
        String[] products={"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord="mouse";
        suggestedProducts(products, searchWord).forEach(
            prd->{
                prd.forEach(p->System.out.print(p+" "));
                System.out.println();
            }
        );
    }
}
