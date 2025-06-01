package JAVA.Luogu;

import java.util.*;

public class excepthandle {
    static int[] Except(int[] A,int N,int i){
        int[] exc=new int[N-1];
        for(int s=0;s<N-1;s++){
            exc[s]=A[s<i?s:s+1];
        }
        return exc;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),A[]=new int[N];
        for(int i=0;i<N;A[i++]=sc.nextInt());
        for(int i=0;i<N;i++){
            int[] ans=Except(A, N, i);
            Arrays.sort(ans);
            System.out.println(ans[N-2]);
        }
        sc.close();
    }
}
