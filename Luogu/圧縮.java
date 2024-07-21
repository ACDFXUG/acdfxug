package Luogu;

import java.util.Scanner;

public class 圧縮 {
    static int MAX(int a,int b){
        return a>b?a:b;
    }
    static int MIN(int a,int b){
        return a<b?a:b;
    }
    static int[] Mb(int[] A){
        int N=A.length;
        int[] ans=new int[N-1];
        for(int i=0;i<N-1;i++){
            ans[i]=MAX(A[i],A[i+1]);
        }
        return ans;
    }
    static int[] mb(int[] A){
        int N=A.length;
        int[] ans=new int[N-1];
        for(int i=0;i<N-1;i++){
            ans[i]=MIN(A[i],A[i+1]);
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),A[]=new int[N],B[]=A;;
        for(int i=0;i<N;A[i++]=sc.nextInt());
        String S=sc.next();
        int l=S.length();
        for(int i=0;i<l;i++){
            char x=S.charAt(i);
            if(x=='M'){
                if(B.length!=1)
                B=Mb(B);
            }else if(x=='m'){
                if(B.length!=1)
                B=mb(B);
            }
            
        }
        System.out.println(B[0]);
        sc.close();
    }
}
