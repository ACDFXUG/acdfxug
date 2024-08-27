package Luogu;

import java.util.*;
import java.util.regex.*;

public class DoubleCamelCase {
    static final String word="[A-Z][a-z]*[A-Z]";
    static final Pattern WORD=Pattern.compile(word);
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String sentence=sc.next();
        StringBuilder ans=new StringBuilder();
        List<String> words=new ArrayList<>();
        for(Matcher wd=WORD.matcher(sentence);
        wd.find();words.add(wd.group()));
        words.sort(String::compareToIgnoreCase);
        words.forEach(ans::append);
        System.out.println(ans);
        sc.close();
    }
}
