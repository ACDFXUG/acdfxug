package JAVA.Luogu;

import java.util.*;

public class Bank {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        var a=new LinkedList<Integer>(){{
            for(int i=1;i<=n;add(i++));
        }};
        var b=new TreeSet<Integer>();
        while(m-->0){
            int act=sc.nextInt();
            switch(act){
                case 1->b.add(a.poll());
                case 2->b.remove(sc.nextInt());
                case 3->System.out.println(b.first());
            }
        }
        sc.close();
    }
}
