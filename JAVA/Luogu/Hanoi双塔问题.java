package JAVA.Luogu;

import java.math.*;
import java.util.*;
import static java.math.BigInteger.*;

public class Hanoi双塔问题 {
    //hanoi(n)是2n个圆盘移动的次数
    static BigInteger hanoi(int n){
        // BigInteger[] ans=new BigInteger[n+1];
        // ans[0]=ZERO;
        // ans[1]=TWO;
        // for(int i=2;i<=n;i++){
        //     ans[i]=ans[i-1].shiftLeft(1).add(TWO);
        // }
        // return ans[n];
        if(n==1) return TWO;
        return hanoi(n-1).shiftLeft(1).add(TWO);
    }
    public static void main(String[] args) {
        try(Scanner sc=new Scanner(System.in)){
            int n=sc.nextInt();
            System.out.println(hanoi(n));
        }
    }
}
