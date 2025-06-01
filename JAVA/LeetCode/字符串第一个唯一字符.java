package JAVA.LeetCode;

import java.util.Scanner;

public class 字符串第一个唯一字符 {
    static int[] char_num(String s){
        int[] num=new int[26];
        for(int i=0;i<s.length();i++){
            num[s.charAt(i)-'a']++;
        }
        return num;
    }
    static int firstUniqChar(String s){
        int min=0x7fffffff,num[]=char_num(s);
        for(int p,i=0;i<26;i++){
            if(num[i]==1){
                p=s.indexOf(i+'a');
                min=p<min?p:min;
            }
        }
        return min==0x7fffffff?-1:min;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        System.out.println(firstUniqChar(s));
        sc.close();
        
    }
}
