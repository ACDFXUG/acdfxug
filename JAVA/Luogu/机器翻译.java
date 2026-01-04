package Java.Luogu;

import java.util.*;

public class 机器翻译 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int M=sc.nextInt(),N=sc.nextInt();
        int ans=0;
        Deque<Integer> mem=new ArrayDeque<>(M);
        for(int i=0;i<N;i++){
            int word=sc.nextInt();
            if(mem.size()<M){
                if(!mem.contains(word)){
                    mem.add(word);
                    ans++;
                }
            }else{
                if(!mem.contains(word)){
                    mem.poll();
                    mem.add(word);
                    ans++;
                }
            }
        }
        System.out.println(ans);
        sc.close();
    }
}
