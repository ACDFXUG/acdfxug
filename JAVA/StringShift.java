package JAVA;

import java.util.*;

public class StringShift {
    static String shiftLeft(String s,int k){
        return s.substring(k)+s.substring(0, k);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Set<String> shift=new TreeSet<>();
        String s=sc.next();
        for(int i=0;i<s.length();i++){
            shift.add(shiftLeft(s, i));
        }
        String[] ans=shift.toArray(new String[shift.size()]);
        System.out.println(ans[0]+" "+ans[ans.length-1]);
        sc.close();
    }
}
