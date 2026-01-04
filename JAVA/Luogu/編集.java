package Java.Luogu;

import java.util.Scanner;

public class 編集 {
    static void change(int[] a,int l,int r,int k){
        for(int i=l-1;i<=r-1;i++){
            a[i]=k;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt(),a[]=new int[N],P=sc.nextInt();
        for(int l,r,k;P>0;P--){
            l=sc.nextInt();
            r=sc.nextInt();
            k=sc.nextInt();
            change(a, l, r, k);
        }
        for(int x:a){
            System.out.println(x);
        }
        sc.close();
    }
}
