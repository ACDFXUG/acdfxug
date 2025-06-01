package JAVA.Luogu;

import java.util.regex.*;
import java.util.*;

public class 求和str {
    static final Pattern NUMBER=Pattern.compile("(?<!\\d)(-?\\d+)(?!\\d)");
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(sc.hasNextLine()){
            String s=sc.nextLine();
            int sum=0;
            boolean find=false;
            for(var m=NUMBER.matcher(s);m.find();){
                sum+=Integer.parseInt(m.group());
                if(!find) find=true;
            }
            if(find) System.out.println(sum);
        }
        sc.close();
    }
}
