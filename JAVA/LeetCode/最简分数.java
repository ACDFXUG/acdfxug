package Java.LeetCode;

import java.util.*;

public class 最简分数 {
    static int gcd(int a,int b){
        return b==0?a:gcd(b,a%b);
    }
    static List<String> simplifiedFractions(int n) {
        var frac=new ArrayList<String>(n*(n-1)>>1);
        var dup=new HashSet<String>(n*(n-1)>>1);
        if(n==1){
            return frac;
        }
        for(int i=2;i<=n;i++){
            for(int j=1;j<i;j++){
                int g=gcd(i,j);
                String s=j/g+"/"+i/g;
                if(!dup.contains(s)){
                    frac.add(s);
                    dup.add(s);
                }
            }
        }
        return frac;
    }
    public static void main(String[] args) {
        simplifiedFractions(6).forEach(System.out::println);

    }
}
