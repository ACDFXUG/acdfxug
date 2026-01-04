package Java.Luogu;

import java.util.*;
import java.util.regex.*;

public class 幂次方 {
    static final String NUMBER="\\((\\d+)\\)";
    static final Pattern NUM=Pattern.compile(NUMBER);
    static String toSTDBin(int n){
        String bin=Integer.toBinaryString(n);
        StringJoiner sum=new StringJoiner("+");
        for(int l=bin.length(),i=0;i<l;i++){
            if(bin.charAt(i)=='1'){
                int index=l-i-1;
                sum.add(index==1?"2":"2("+index+")");
            }
        }
        return sum.toString();
    }
    static String expoPow(int n){
        String std=toSTDBin(n);
        boolean flag;
        StringBuilder ans=new StringBuilder();
        do{
            flag=false;
            Matcher tmp=NUM.matcher(std);
            while(tmp.find()){
                int number=Integer.parseInt(tmp.group(1));
                if(number>2){
                    String newNum=toSTDBin(number);
                    tmp.appendReplacement(ans,"("+newNum+")");
                    flag=true;
                }
            }
            tmp.appendTail(ans);
            std=ans.toString();
            ans.setLength(0);
        }while(flag);
        return std;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println(expoPow(sc.nextInt()));
        sc.close();
    }
}
