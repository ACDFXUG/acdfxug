package JAVA.Luogu;

import java.util.*;
import java.util.stream.*;

public class AverageNumbers {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),sum=0,FINAL,L;
        int[] a=new int[n];
        for(int i=0;i<n;i++){
            a[i]=sc.nextInt();
            sum+=a[i];
        }
        FINAL=sum;
        int[] ans=IntStream.range(0,n)
        .parallel()
        .filter(i->a[i]*n==FINAL)
        .map(i->i+1)
        .toArray();
        Arrays.sort(ans);
        System.out.println(L=ans.length);
        if(L!=0){
            for(int x:ans){
                System.out.print(String.valueOf(x));
            }
            System.out.println();
        }
        sc.close();
    }
}
