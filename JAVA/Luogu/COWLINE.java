package Java.Luogu;

import java.util.*;

public class COWLINE {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);sc.close();
        int S=sc.nextInt(),t=0;
        Deque<Integer> init=new ArrayDeque<Integer>();
        for(;S>0;S--){
            char act=sc.next().charAt(0);
            if(act=='A'){
                char pos=sc.next().charAt(0);
                t++;
                switch(pos){
                    case'L'->init.addFirst(t);
                    case'R'->init.addLast(t);
                }
            }else if(act=='D'){
                char pos=sc.next().charAt(0);
                switch(pos){
                    case'L'->{int k=sc.nextInt();for(;k>0;k--)init.removeFirst();}
                    case'R'->{int k=sc.nextInt();for(;k>0;k--)init.removeLast();}
                }
            }
        }
        for(int x:init){
            System.out.println(x);
        }
    }
}
