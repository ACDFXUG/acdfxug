package Java.Luogu;

import java.util.Scanner;

public class 缘分计算 {
    static String LOVE(String abbr,int ST){
        String ans="";
        char[] p=abbr.toCharArray();
        for(int i=0;i<p.length;i++){
            ans+=(ST+p[i]-65);
        }
        return ans;
    }
    static String CALCULATE(String ANS){
        String end="";
        char[] p=ANS.toCharArray();
        for(int i=p.length-1;i>0;i--){
            int plus=(p[i]-'0'+p[i-1]-'0')%10;
            end=plus+end;
        }
        return end;
    }
    static String FATE(String abbr,int ST){
        String love=LOVE(abbr, ST);
        for(;true;){
            love=CALCULATE(love);
            if(love.equals("100")){
                return love;
            }
            if(love.length()<=2){
                return love;
            }
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String ABB=sc.next();
        int ST=sc.nextInt();
        System.out.println(Integer.parseInt(FATE(ABB, ST)));
        sc.close();
    }
}
