package JAVA.Luogu;

import java.util.*;

public class MARKO {
    static final HashMap<Character,Integer> marko=
        new HashMap<Character,Integer>(26){{
            put('a',2);put('b',2);put('c',2);
            put('d',3);put('e',3);put('f',3);
            put('g',4);put('h',4);put('i',4);
            put('j',5);put('k',5);put('l',5);
            put('m',6);put('n',6);put('o',6);
            put('p',7);put('q',7);put('r',7);put('s',7);
            put('t',8);put('u',8);put('v',8);
            put('w',9);put('x',9);put('y',9);put('z',9);
        }};
    static Set<Integer> getMarko(String s){
        var ans=new HashSet<Integer>();
        for(int i=0;i<s.length();i++){
            ans.add(marko.get(s.charAt(i)));
        }
        return ans;
    }
    static Set<Integer> getMarko1(String num){
        var ans=new HashSet<Integer>();
        for(int i=0;i<num.length();i++){
            ans.add(num.charAt(i)-'0');
        }
        return ans;
    }
    public static void main(String[] args) {
        var sc=new Scanner(System.in);
        int N=sc.nextInt();
        var s=new String[N];
        var markos=new ArrayList<Set<Integer>>(N);
        for(int i=0;i<N;i++){
            s[i]=sc.next();
            markos.add(getMarko(s[i]));
            System.out.println(markos.get(i));
        }
        String num=sc.next();
        var nums=getMarko1(num);
        System.out.println(nums);
        int ans=0;
        for(int i=0;i<N;i++){
            if(nums.containsAll(markos.get(i))){
                ans++;
            }
        }
        System.out.println(ans);
        sc.close();
    }
}
