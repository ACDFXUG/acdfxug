package JAVA.Luogu;

import java.util.*;
import static java.lang.Character.isDigit;

@SuppressWarnings({"unused"})
public class PostalCode {
    static boolean hasNonNum(String s){
        int l=0,r=s.length()-1;
        for(;l<=r;l++,r--){
            char a=s.charAt(l),b=s.charAt(r);
            if(!isDigit(a)||!isDigit(b)) return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int A=sc.nextInt(),B=sc.nextInt();
        String postal=sc.next(); //len=A+B+1
        char c=postal.charAt(A);
        if(c=='-'){
            String[] split={postal.substring(0,A),postal.substring(A+1)};
            if((!hasNonNum(split[0]))&&(!hasNonNum(split[1]))){
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }
        }else{
            System.out.println("No");
        }
        sc.close();
    }
}
