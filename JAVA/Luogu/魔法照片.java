package JAVA.Luogu;

import java.util.*;

public class 魔法照片 {
    private static class Friend{
        final int rank;
        int W,D,C;
        Friend(int rank,int w){
            this.rank=rank;
            this.W=w;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),k=sc.nextInt();
        final int[] E=new int[10];
        Friend[] frds=new Friend[n];
        for(int i=0;i<10;E[i++]=sc.nextInt());
        for(int i=0;i<n;i++){
            frds[i]=new Friend(i+1,sc.nextInt());
        }
        sc.close();
        Arrays.sort(frds,(f1,f2)->f2.W-f1.W);
        for(int i=0;i<n;i++){
            var f=frds[i];
            f.D=i+1;
            f.C=(f.D-1)%10+1;
            f.W+=E[f.C-1];
        }
        Arrays.stream(frds).sorted((f1,f2)->{
            return f1.W==f2.W?f1.rank-f2.rank:f2.W-f1.W;
        }).limit(k).forEach(F->System.out.print(F.rank+" "));
    }
}
