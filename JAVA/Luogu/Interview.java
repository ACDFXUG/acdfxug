package JAVA.Luogu;

import java.util.Scanner;

public class Interview {
   
    static int F(int[] X,int L,int R){
        int ans=X[L-1];
        for(int i=L;i<R;ans|=X[i++]);
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),A[]=new int[N],B[]=new int[N];
        for(int i=0;i<N;A[i++]=sc.nextInt());
        for(int i=0;i<N;B[i++]=sc.nextInt());
        int L=1,R=N,sum=0,max=0x80000000;
        for(L=1;L<=N;L++){
            for(R=N;R>L;R--){
                sum=F(A,L,R)+F(B,L,R);
                max=sum>max?sum:max;
            }
        }
        System.out.println(max);
        sc.close();
    }
}
