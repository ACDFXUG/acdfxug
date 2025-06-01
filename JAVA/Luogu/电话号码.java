package JAVA.Luogu;

import java.util.*;

public class 电话号码 {
    static String toStandardPhoneNumber(String p){
        StringBuilder phone=new StringBuilder();
        for(int i=0,l=p.length();i<l;i++){
            char c=p.charAt(i);
            phone.append(
                switch(c){
                    case 'A','B','C'->'2';
                    case 'D','E','F'->'3';
                    case 'G','H','I'->'4';
                    case 'J','K','L'->'5';
                    case 'M','N','O'->'6';
                    case 'P','R','S'->'7';
                    case 'T','U','V'->'8';
                    case 'W','X','Y'->'9';
                    case '-'->"";
                    default ->c;
                }
            );
        }
        return phone.insert(3,'-').toString();
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        TreeMap<String,Integer> dup=new TreeMap<>(String::compareTo);
        int N=sc.nextInt();
        while(N-->0){
            String phone=toStandardPhoneNumber(sc.next());
            dup.put(phone,dup.getOrDefault(phone,0)+1);
        }
        List<Map.Entry<String,Integer>> ans=dup.entrySet().stream()
        .filter(E->E.getValue()>1)
        .toList();
        if(ans.isEmpty()){
            System.out.println("No duplicates.");
        }else{
            for(Map.Entry<String,Integer> E:ans){
                System.out.println(E.getKey()+" "+E.getValue());
            }
        }
        sc.close();
    }
}
