package Luogu;

import java.util.*;

public class 奖学金 {
    static int RANK=1;
    private static class Grades implements Comparable<Grades> {
        int Chinese,Math,English;
        int SUM;
        int rank;
        Grades(int Chinese,int Math,int English){
            this.Chinese=Chinese;
            this.Math=Math;
            this.English=English;
            this.SUM=Chinese+Math+English;
            this.rank=RANK++;
        }
        public int compareTo(Grades stu){
            if(SUM!=stu.SUM){
                return stu.SUM-SUM;
            }else{
                if(Chinese!=stu.Chinese){
                    return stu.Chinese-Chinese;
                }else{
                    return rank-stu.rank;
                }
            }
        }
        int getMath(){
            return Math;
        }
        int getEnglish(){
            return English;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        Grades[] grade=new Grades[N];
        for(int i=0;i<N;i++){
            grade[i]=new Grades(sc.nextInt(),sc.nextInt(),sc.nextInt());
        }
        Arrays.sort(grade);
        for(int i=0;i<5;i++){
            System.out.printf("%d %d\n",grade[i].rank,grade[i].SUM);
        }
        System.out.println(grade[0].getMath()+" "+grade[0].getEnglish());
        sc.close();
    }
}
