package Luogu;

import java.util.*;

public class 影子字符串 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Set<String> shadow=new LinkedHashSet<>(500);
        while(sc.hasNext()){
            String shdw=sc.next();
            if(shdw.equals("0")){
                break;
            }else{
                shadow.add(shdw);
            }
        }
        shadow.forEach(System.out::print);
        sc.close();
    }
}
