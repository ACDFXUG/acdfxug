package Java.Luogu;

import java.util.*;

public class 亲戚 {
    // 并查集
    static int find(int[] S,int x){
        int rt=x;
        for(;S[rt]>=0;rt=S[rt]);
        for(;x!=rt;){
            int t=S[x];
            S[x]=rt;
            x=t;
        }
        return rt;
    }
    static void union(int[] S,int x,int y){
        if(x==y) return;
        if(S[x]<S[y]){
            S[x]+=S[y];
            S[y]=x;
        }else{
            S[y]+=S[x];
            S[x]=y;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt(),
            p=sc.nextInt();
        int[] S=new int[n+1];
        for(int i=0;i<=n;S[i++]=-1);
        for(int i=0;i<m;i++){
            int x=sc.nextInt(),y=sc.nextInt();
            union(S,find(S,x),find(S,y));
        }
        for(int i=0;i<p;i++){
            int x=sc.nextInt(),y=sc.nextInt();
            System.out.println(find(S,x)==find(S,y)?"Yes":"No");
        }
        sc.close();
    }
}
