package Luogu;

import java.util.*;
import java.util.regex.*;

public class 压缩技术改 {
    static List<String> one(String zip){
        List<String> one=new ArrayList<>();
        String reg="1+";
        Matcher m=Pattern.compile(reg).matcher(zip);
        for(;m.find();one.add(m.group()));
        return one;
    }
    static List<String> zero(String zip){
        List<String> zero=new ArrayList<>();
        String reg="0+";
        Matcher m=Pattern.compile(reg).matcher(zip);
        for(;m.find();zero.add(m.group()));
        return zero;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String zip=sc.next();
        int l=zip.length();
        for(int i=1;i<l;i++){
            zip=zip+sc.next();
        }
        List<String> zero=zero(zip);
        List<String> one=one(zip);
        System.out.print(l);
        if(zip.startsWith("0")){
            for(int i=0;i<Math.max(zero.size(),one.size());i++){
                if(i<zero.size()){
                    System.out.print(" "+zero.get(i).length());
                }
                if(i<one.size()){
                    System.out.print(" "+one.get(i).length());
                }
            }
        }else{
            System.out.print(" 0");
            for(int i=0;i<Math.max(zero.size(),one.size());i++){
                if(i<one.size()){
                    System.out.print(" "+one.get(i).length());
                }
                if(i<zero.size()){
                    System.out.print(" "+zero.get(i).length());
                }
            }
        }
        System.out.println();
        sc.close();
    }
}
