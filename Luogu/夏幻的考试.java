package Luogu;

import java.util.*;

public class 夏幻的考试 {
    static final 
    Map<String,Character> answer=new HashMap<>(){{
        put("1000",'A');
        put("0100",'B');
        put("0010",'C');
        put("0001",'D');
    }};
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt(),n=sc.nextInt();
        String[] answers=new String[n];
        String ans=sc.next();
        while(T-->0){
            int id=Integer.parseInt(sc.next(),2);
            String AB=sc.next();
            for(int i=0;i<n;i++){
                answers[i]=sc.next();
            }
            if(id<1||id>10000){
                System.out.println("Wrong ID\n");
                continue;
            }
            System.out.println("ID: "+id);
            if(AB.equals("00")||AB.equals("11")){
                System.out.println("Type Incorrect");
            }else if(AB.equals("01")){
                System.out.println((id&1)==1?
                "Type Correct":"Type Incorrect");
            }else if(AB.equals("10")){
                System.out.println((id&1)==1?
                "Type Incorrect":"Type Correct");
            }
            double score=.0;
            for(int i=0;i<n;i++){
                char a=ans.charAt(i);
                if(answer.getOrDefault(answers[i],'#')==a){
                    score+=100;
                }
            }
            System.out.printf("%.1f\n\n",score/n);
        }
        sc.close();
    }
}
