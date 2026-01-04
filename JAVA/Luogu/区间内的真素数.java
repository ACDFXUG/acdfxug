package Java.Luogu;

import java.util.*;

public class 区间内的真素数 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int M=sc.nextInt(),N=sc.nextInt();
        BitSet prime=new BitSet(100001){{
            set(2,100001);
        }};
        for(int i=2;i*i<=100001;i++){
            if(prime.get(i)){
                for(int j=i*i;j<=100001;j+=i){
                    prime.clear(j);
                }
            }
        }
        StringJoiner ans=new StringJoiner(",");
        for(int i=M;i<=N;i++){
            if(prime.get(i)){
                String I=String.valueOf(i);
                int rev=Integer.parseInt(
                    new StringBuilder(I)
                        .reverse()
                        .toString());
                if(prime.get(rev)){
                    ans.add(I);
                }
            }
        }
        System.out.println(ans.length()==0?"No":ans);
        sc.close();
    }
}
