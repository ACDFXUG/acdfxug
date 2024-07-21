package Luogu;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DZYHash {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int p=sc.nextInt(),n=sc.nextInt();
        int[] num=new int[n];
        for(int i=0;i<n;i++){
            num[i]=sc.nextInt();
        }
        boolean end=true;
        Set<Integer> set=new HashSet<>(0);
        for(int i=1;i<=n;i++){
            int l=set.size();
            set.add(num[i-1]%p);
            if(set.size()==l){
                System.out.println(i);
                end=false;
                break;
            }
        }
        if(end){
            System.out.println(-1);
        }
        sc.close();
    }  
}
