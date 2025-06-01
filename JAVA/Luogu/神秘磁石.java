package JAVA.Luogu;

import java.util.*;

public class 神秘磁石 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),k=sc.nextInt();
        BitSet prime=new BitSet(n+1);
        prime.set(2,n+1);
        for(int i=2;i*i<=n;i++){
            if(prime.get(i)){
                for(int j=i*i;j<=n;j+=i){
                    prime.clear(j);
                }
            }
        }
        boolean flag=false;
        for(int p=2;p<=n-k;p++){
            if(prime.get(p)&&prime.get(p+k)){
                System.out.println(p+" "+(p+k));
                flag=true;
            }
        }
        if(!flag){
            System.out.println("empty");
        }
        sc.close();
    }
}
