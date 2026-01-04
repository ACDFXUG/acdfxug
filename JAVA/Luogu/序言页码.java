package Java.Luogu;

import java.util.*;

public class 序言页码 {
    static final String[] ROMAN={
        "M","CM","D","CD","C","XC",
        "L","XL","X","IX","V","IV","I"
    };
    static final int[] NUMS={
        1000,900,500,400,100,
        90,50,40,10,9,5,4,1
    };
    static StringBuilder intToRoman(int roman){
        StringBuilder romanStr=new StringBuilder();
        for(int i=0;i<13;i++){
            while(roman>=NUMS[i]){
                roman-=NUMS[i];
                romanStr.append(ROMAN[i]);
            }
        }
        return romanStr;
    }
    static Map<Character,Integer> charCounter(int roman){
        Map<Character,Integer> counter=new HashMap<>();
        var sb=intToRoman(roman);
        for(int i=0;i<sb.length();i++){
            counter.merge(sb.charAt(i),1,Integer::sum);
        }
        return counter;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Map<Character,Integer> counter=new HashMap<>(),
            charIndex=new HashMap<>(){{
                put('I',0);put('V',1);put('X',2);
                put('L',3);put('C',4);put('D',5);
                put('M',6);
            }};
        for(int i=1;i<=n;i++){
            charCounter(i).forEach(
                (ch,cnt)->counter.merge(ch,cnt,Integer::sum)
            );
        }
        sc.close();
        counter.entrySet().stream()
            .sorted((E1,E2)->
                charIndex.get(E1.getKey())-charIndex.get(E2.getKey())
            ).forEach(E->System.out.println(E.getKey()+" "+E.getValue()));
    }
}
