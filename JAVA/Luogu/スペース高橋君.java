package Java.Luogu;

import java.util.*;

public class スペース高橋君 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        String[] split=str.split("\\s");
        StringJoiner sj=new StringJoiner("\s");
        for(String s:split){
            if(s.equals("Left")){
                sj.add("<");
            }else if(s.equals("Right")){
                sj.add(">");
            }else{
                sj.add("A");
            }
        }
        System.out.println(sj);
        sc.close();
    }
}
