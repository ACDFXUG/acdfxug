package JAVA.Luogu;

import java.util.*;

public class ZIGZAG {
    private static class ZigZag
    implements Comparable<ZigZag>{
        String word;
        int cnt;
        ZigZag(String word){
            this.word=word;
            this.cnt=0;
        }
        public int compareTo(ZigZag zg){
            return cnt==zg.cnt?
            word.compareTo(zg.word):cnt-zg.cnt;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int K=sc.nextInt(),N=sc.nextInt();
        Map<Character,PriorityQueue<ZigZag>> zig=new HashMap<>();
        for(int i=0;i<K;i++){
            String word=sc.next();
            char ch=word.charAt(0);
            if(zig.containsKey(ch)){
                zig.get(ch).add(new ZigZag(word));
            }else{
                zig.put(ch,new PriorityQueue<ZigZag>(50000){{
                    add(new ZigZag(word));
                }});
            }
        }
        for(int i=0;i<N;i++){
            char op=sc.next().charAt(0);
            ZigZag zg=zig.get(op).poll();
            System.out.println(zg.word);
            zg.cnt++;
            zig.get(op).add(zg);
        }
        sc.close();
    }
}
