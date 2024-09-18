package Luogu;

import java.util.Scanner;
import java.util.regex.*;

public class HarpTuning {
    static final Pattern HARP=
    Pattern.compile("([A-Z]+)([+-])(\\d+)");
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String harp=sc.next();
        for(Matcher tune=HARP.matcher(harp);
        tune.find();){
            String string=tune.group(1),
            sign=tune.group(2),
            num=tune.group(3);
            System.out.println(
                switch(sign){
                    case "+"->string+" tighten "+num;
                    case "-"->string+" loosen "+num;
                    default->"Invalid sign";
                }
            );
        }
        sc.close();
    }
}
