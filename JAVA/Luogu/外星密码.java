package Java.Luogu;

import java.util.*;
import java.util.regex.*;

public class 外星密码 {
    static final Pattern ZIP=Pattern.compile("\\[(\\d+)([A-Z]+)\\]");
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String zip=sc.next();
        while(zip.contains("[")){
            Matcher zipped=ZIP.matcher(zip);
            for(;zipped.find();){
                int n=Integer.parseInt(zipped.group(1));
                String s=zipped.group(2);
                zip=zip.replace(zipped.group(),s.repeat(n));
            }
        }
        System.out.println(zip);
        sc.close();
    }
}
