package LeetCode;

import java.util.Scanner;

public class 补数 {
    public static int findComplement(int num){
        String s=Integer.toBinaryString(num);
        int com=0; 
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='0'){
                com+=1<<(s.length()-i-1);
            }
        }
        return com;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(findComplement(n));
        sc.close();
    }
}
