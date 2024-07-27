package Luogu;

import java.util.*;

public class 不重复数字 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        while(T-->0){
            int n=sc.nextInt();
            Set<Integer> ans=new LinkedHashSet<Integer>(){{
                for(int i=0;i<n;i++){
                    add(sc.nextInt());
                }
            }};
            int i[]={0},l=ans.size();
            ans.forEach(I->{
                System.out.print(i[0]++==l-1?I+"\n":I+" ");
                
            });
        }
        sc.close();
    }
}
