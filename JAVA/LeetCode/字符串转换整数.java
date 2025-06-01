package JAVA.LeetCode;

import java.util.Scanner;

public class 字符串转换整数 {
    static boolean isNum(char c){
        return c>='0'&&c<='9';
    }
    static int myAtoi(String num){
        num=num.replaceAll("^ +", "");
        if(num==null||num.length()==0){
            return 0;
        }
        StringBuilder sb=new StringBuilder();
        int sign=1;
        if(num.charAt(0)=='-'){
            sign=-1;
        }else{
            sign=1;
        }
        if(num.charAt(0)=='-'||num.charAt(0)=='+'){
            num=num.substring(1);
            for(int i=0;i<num.length();i++){
                char ch=num.charAt(i);
                if(isNum(ch)){
                    sb.append(ch);
                }else{
                    break;
                }
            }
        }else{
            for(int i=0;i<num.length();i++){
                char ch=num.charAt(i);
                if(isNum(ch)){
                    sb.append(ch);
                }else{
                    break;
                }
            }
        }
        String ans=sb.toString().replaceAll("^0+", "");
        if(ans.length()==0){
            return 0;
        }else{
            if(ans.length()>10){
                return sign==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
            }else if(ans.length()==10){
                long res=Long.parseLong(ans)*sign;
                if(res>Integer.MAX_VALUE){
                    return Integer.MAX_VALUE;
                }else if(res<Integer.MIN_VALUE){
                    return Integer.MIN_VALUE;
                }else{
                    return (int)res;
                }
            }else{
                return sign*Integer.parseInt(ans);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String num=sc.nextLine();
        System.out.println(myAtoi(num));
        sc.close();
    }
}
