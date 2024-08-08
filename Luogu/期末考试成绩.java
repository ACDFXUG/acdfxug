package Luogu;

import java.util.Scanner;

public class 期末考试成绩 {
    static String GPA(int score){
        if(score>=90){
            return "4.0";
        }else if(score>=60&&score<90){
            int delta=90-score;
            return Double.toString(4.0-delta*0.1);
        }else{
            score=10*((int)Math.sqrt(score));
            if(score<60){
                return "0.0";
            }else{
                return GPA(score);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int score=sc.nextInt();
        System.out.println(GPA(score));
        sc.close();
    }
}
