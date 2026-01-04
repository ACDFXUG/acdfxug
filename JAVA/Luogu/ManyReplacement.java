package Java.Luogu;

import java.util.*;

public class ManyReplacement {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        char[] chars=sc.next().toCharArray();
        List<List<Integer>> index=new ArrayList<List<Integer>>(){{
            for(int i=0;i<26;i++){
                add(new ArrayList<Integer>());
            }
        }};
        for(int i=0;i<N;i++){
            index.get(chars[i]-'a').add(i);
        }
        int Q=sc.nextInt();
        while(Q-->0){
            char a=sc.next().charAt(0);
            char b=sc.next().charAt(0);
            index.get(b-'a').addAll(index.get(a-'a'));
            for(int idx:index.get(a-'a')){
                chars[idx]=b;
            }
            index.get(a-'a').clear();
        }
        System.out.println(new String(chars));
        sc.close();
    }
}
