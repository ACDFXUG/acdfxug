package Java.Luogu;

import java.util.*;
import java.util.regex.*;

public class QQsolver {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String mul=sc.next();
        String reg="(\\d)x(\\d)";
        Matcher m=Pattern.compile(reg).matcher(mul);
        if(m.matches()){
            int a=Integer.parseInt(m.group(1));
            int b=Integer.parseInt(m.group(2));
            System.out.println(a*b);
        }
        sc.close();
    }
}
