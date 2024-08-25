package Luogu;

import java.util.*;
import java.util.regex.*;

public class 方程求解 {
    static final String number="[+-]?\\b\\d+(?!x)\\b";
    static final String x="([+-]?[1-9])x";
    static final Pattern NUMBER=Pattern.compile(number);
    static final Pattern X=Pattern.compile(x);
    static int getSolution(String equation){
        String[] eq=equation.split("=");
        int scalarX=0,scalar=0;
        int right=Integer.parseInt(eq[1]);
        for(Matcher num=NUMBER.matcher(eq[0]);num.find();){
            scalar+=Integer.parseInt(num.group());
        }
        for(Matcher eqX=X.matcher(eq[0]);eqX.find();){
            scalarX+=Integer.parseInt(eqX.group(1));
        }
        return (right-scalar)/scalarX;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        List<Integer> solution=new ArrayList<>();
        int n=sc.nextInt(),q=sc.nextInt();
        for(int i=0;i<n;i++){
            solution.add(getSolution(sc.next()));
        }
        while(q-->0){
            int l=sc.nextInt(),r=sc.nextInt();
            int ans=0;
            for(int x:solution){
                if(x>=l&&x<=r) ans++;
            }
            System.out.println(ans);
        }
        sc.close();
    }
}
