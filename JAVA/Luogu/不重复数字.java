package Java.Luogu;

import java.util.*;

public class 不重复数字 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        while(T-->0){
            int n=sc.nextInt();
            List<Integer> list=new ArrayList<>();
            for(int i=0;i<n;i++){
                list.add(sc.nextInt());
            }
            list.stream().distinct().forEach(I->System.out.print(I+" "));
            System.out.println();
        }
        sc.close();
    }
}
