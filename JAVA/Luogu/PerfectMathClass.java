package JAVA.Luogu;

import java.util.*;
import java.util.function.*;

public class PerfectMathClass {
    static int powx(int x,int n){
        int ans=1;
        for(;n>0;n>>=1){
            if((n&1)==1) ans*=x;
            x*=x;
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt(),
            k=sc.nextInt();
        char[][] pic=new char[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                pic[i][j]='.';
            }
        }
        int[] a=new int[k+1];
        for(int i=0;i<=k;i++){
            a[i]=sc.nextInt();
        }
        Function<Integer,Integer> fx=x->{
            int ans=0;
            for(int i=0;i<=k;i++){
                ans+=powx(x,i)*a[i];
            }
            return ans;
        };
        for(int j=0;j<n;j++){
            int y=fx.apply(j);
            if(y<m&&y>=0){
                pic[m-y-1][j]='*';
            }
        }
        for(var l:pic){
            System.out.println(l);
        }
        sc.close();
    }
}
