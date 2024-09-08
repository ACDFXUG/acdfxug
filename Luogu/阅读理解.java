package Luogu;

import java.util.*;

public class 阅读理解 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        Map<Integer,HashSet<String>> words=new HashMap<>(N);
        for(int i=0;i<N;){
            int cnt=sc.nextInt();
            HashSet<String> word=new HashSet<>(cnt);
            for(int j=0;j<cnt;j++){
                word.add(sc.next());
            }
            words.put(++i,word);
        }
        int M=sc.nextInt();
        while(M-->0){
            String s=sc.next();
            StringJoiner ans=new StringJoiner("\s");
            for(int i=1;i<=N;i++){
                if(words.get(i).contains(s)){
                    ans.add(Integer.toString(i));
                }
            }
            System.out.println(ans);
        }
        sc.close();
    }
}
