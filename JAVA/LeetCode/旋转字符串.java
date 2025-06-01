package JAVA.LeetCode;

import java.util.Scanner;

public class 旋转字符串 {
    static boolean rotateString(String s,String goal){
        int L=s.length();
        for(int i=0;i<L;i++){
            if(s.equals(goal)){
                return true;
            }else{
                s=s.substring(1, L)+s.charAt(0);
            }
        }
        return false;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next(),goal=sc.next();
        System.out.println(rotateString(s, goal));
        sc.close();
    }
}
