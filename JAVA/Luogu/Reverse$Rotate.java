package Java.Luogu;

import java.util.*;

public class Reverse$Rotate {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        StringBuilder ans=new StringBuilder(sc.next());
        int n=sc.nextInt();
        while(n-->0){
            String act=sc.next();
            switch(act){
                case "<"->{
                    int x=sc.nextInt()%ans.length();
                    ans.append(ans.subSequence(0,x));
                    ans.delete(0,x);
                }case ">"->{
                    int x=sc.nextInt()%ans.length();
                    ans.insert(0,ans.subSequence(ans.length()-x,ans.length()));
                    ans.delete(ans.length()-x,ans.length());
                }case "rev"->{
                    ans.reverse();
                }
            }
        }
        System.out.println(ans);
        sc.close();
    }
}
