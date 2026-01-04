package Java.Luogu;

import java.util.*;

public class 集合运算2 {
    static final Set<Integer> U=new HashSet<>(64){{
        for(int i=0;i<=63;add(i++));
    }};
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        final int x=sc.nextInt();
        Set<Integer> A=new HashSet<>(x);
        for(int i=0;i<x;i++){
            A.add(sc.nextInt());
        }
        final int y=sc.nextInt();
        Set<Integer> B=new HashSet<>(y);
        for(int i=0;i<y;i++){
            B.add(sc.nextInt());
        }
        System.out.println(A.size());
        var tmpA=new HashSet<>(A);
        tmpA.retainAll(B);
        tmpA.forEach(i->System.out.print(i+" "));
        System.out.println();
        tmpA=new HashSet<>(A);
        tmpA.addAll(B);
        tmpA.forEach(E->System.out.print(E+" "));
        System.out.println();
        var tmpU=new HashSet<>(U);
        tmpU.removeAll(A);
        tmpU.forEach(E->System.out.print(E+" "));
        System.out.println();
        System.out.println(A.equals(B)?"1":"0");
        System.out.println(B.containsAll(A)?"1":"0");
        System.out.println(A.contains(0)?"1":"0");
        sc.close();
    }
}
