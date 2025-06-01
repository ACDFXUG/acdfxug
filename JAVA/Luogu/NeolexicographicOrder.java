package JAVA.Luogu;

import java.util.*;

public class NeolexicographicOrder {
    static String change(String s,Map<Character,Character> neoOrder){
        StringBuilder sb=new StringBuilder();
        for(int i=0,l=s.length();i<l;i++){
            sb.append(neoOrder.get(s.charAt(i)));
        }
        return sb.toString();
    };
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String lex=sc.next();
        Map<Character,Character> neoOrder=new HashMap<>();
        Map<String,String> neoLex=new HashMap<>();
        for(int i=0,l=lex.length();i<l;i++){
            neoOrder.put(lex.charAt(i),(char)(i+'a'));
        }
        int N=sc.nextInt();
        while(N-->0){
            String str=sc.next();
            neoLex.put(str,change(str,neoOrder));
        }
        neoLex.entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .forEach(E->System.out.println(E.getKey()));
        sc.close();
    }
}
