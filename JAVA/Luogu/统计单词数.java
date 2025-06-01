package JAVA.Luogu;

import java.util.*;
import java.util.regex.*;

public class 统计单词数 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String word=sc.nextLine().toLowerCase();
        String article=sc.nextLine().toLowerCase();
        int count=0,location=-1;
        Matcher matcher=Pattern.compile("\\b"+word+"\\b").matcher(article);
        while(matcher.find()){
            count++;
            if(location==-1) location=matcher.start();
        }
        System.out.println(count==0?-1:count+" "+location);
        sc.close();
    }
}
